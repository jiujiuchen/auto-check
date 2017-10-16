/**
 * Ajax Autocomplete for jQuery, version 1.1.3 (c) 2010 Tomas Kirda
 * 
 * Ajax Autocomplete for jQuery is freely distributable under the terms of an
 * MIT-style license. For details, see the web site:
 * http://www.devbridge.com/projects/autocomplete/jquery/
 * 
 * Last Review: 04/19/2010 2012-06-03:将ajax的返回类型改为json
 * 2012-07-19:增加关联条件查询的自动联想，选择的值去掉空格 2012-08-08
 * 增加了文本框后面的搜索按钮，点击弹出对话框，并将自动完成的参数传入对话框中
 * 
 */

/*
 * jslint onevar: true, evil: true, nomen: true, eqeqeq: true, bitwise: true,
 * regexp: true, newcap: true, immed: true
 */
/*
 * global window: true, document: true, clearInterval: true, setInterval: true,
 * jQuery: true
 */

(function($) {

	var reEscape = new RegExp('(\\'
			+ [ '/', '.', '*', '+', '?', '|', '(', ')', '[', ']', '{', '}',
					'\\' ].join('|\\') + ')', 'g');

	function fnFormatResult(value, data, currentValue) {
		var pattern = '(' + currentValue.replace(reEscape, '\\$1') + ')';
		return value.replace(new RegExp(pattern, 'gi'), '<strong>$1<\/strong>');
	}

	function Autocomplete(el, options) {
		this.el = $(el);
		this.el.attr('autocomplete', 'off');
		this.suggestions = [];
		this.data = [];
		this.badQueries = [];
		this.selectedIndex = -1;
		this.currentValue = this.el.val();
		this.intervalId = 0;
		this.cachedResponse = [];
		this.onChangeInterval = null;
		this.ignoreValueChange = false;
		this.serviceUrl = options.serviceUrl;
		this.isLocal = false;
		this.options = {
			autoSubmit : false,
			minChars : 1,
			maxHeight : 300,
			deferRequestBy : 0,
			width : 0,
			highlight : true,
			params : {},// 初始化的参数
			fnFormatResult : fnFormatResult,
			delimiter : null,
			zIndex : 9999
		};
		// 2012-07-19
		// 添加isUseDefaultCache使用默认缓存，如果为false，则不使用缓存，每次都从数据库中查询，一般用于联合主键联想提示
		this.isUseDefaultCache = true;
		this.initialize();
		this.setOptions(options);
	}

	$.fn.autocomplete = function(options) {

		return new Autocomplete(this.get(0) || $('<input />'), options);
	};

	Autocomplete.prototype = {

		killerFn : null,

		initialize : function() {

			var me, uid, autocompleteElId;
			me = this;
			uid = Math.floor(Math.random() * 0x100000).toString(16);
			autocompleteElId = 'Autocomplete_' + uid;

			this.killerFn = function(e) {
				if ($(e.target).parents('.autocomplete').size() === 0) {
					me.killSuggestions();
					me.disableKillerFn();
				}
			};

			if (!this.options.width) {
				this.options.width = this.el.width();
			}
			this.mainContainerId = 'AutocompleteContainter_' + uid;

			$(
					'<div id="'
							+ this.mainContainerId
							+ '" style="position:absolute;z-index:9999;"><div class="autocomplete-w1"><div class="autocomplete" id="'
							+ autocompleteElId
							+ '" style="display:none; width:300px;"></div></div></div>')
					.appendTo('body');

			this.container = $('#' + autocompleteElId);
			this.fixPosition();
			if (window.opera) {
				this.el.keypress(function(e) {
					me.onKeyPress(e);
				});
			} else {
				this.el.keydown(function(e) {
					me.onKeyPress(e);
				});
			}
			this.el.keyup(function(e) {
				me.onKeyUp(e);
			});
			this.el.blur(function() {
				me.enableKillerFn();
			});
			this.el.focus(function() {
				me.fixPosition();
			});
		},

		setOptions : function(options) {
			var o = this.options;
			$.extend(o, options);
			if (o.lookup) {
				this.isLocal = true;
				if ($.isArray(o.lookup)) {
					o.lookup = {
						suggestions : o.lookup,
						data : []
					};
				}
			}
			$('#' + this.mainContainerId).css({
				zIndex : o.zIndex
			});
			this.container.css({
				maxHeight : o.maxHeight + 'px',
				width : o.width
			});
		},

		clearCache : function() {
			this.cachedResponse = [];
			this.badQueries = [];
		},

		disable : function() {
			this.disabled = true;
		},

		enable : function() {
			this.disabled = false;
		},

		fixPosition : function() {
			var offset = this.el.offset();
			$('#' + this.mainContainerId).css({
				top : (offset.top + this.el.innerHeight()) + 'px',
				left : offset.left + 'px'
			});
		},

		enableKillerFn : function() {
			var me = this;
			$(document).bind('click', me.killerFn);
		},

		disableKillerFn : function() {
			var me = this;
			$(document).unbind('click', me.killerFn);
		},

		killSuggestions : function() {
			var me = this;
			this.stopKillSuggestions();
			this.intervalId = window.setInterval(function() {
				me.hide();
				me.stopKillSuggestions();
			}, 300);
		},

		stopKillSuggestions : function() {
			window.clearInterval(this.intervalId);
		},

		onKeyPress : function(e) {
			if (this.disabled || !this.enabled) {
				return;
			}
			// return will exit the function
			// and event will not be prevented
			switch (e.keyCode) {
			case 27: // KEY_ESC:
				this.el.val(this.currentValue);
				this.hide();
				break;
			case 9: // KEY_TAB:
			case 13: // KEY_RETURN:
				if (this.selectedIndex === -1) {
					this.hide();
					return;
				}
				this.select(this.selectedIndex);
				if (e.keyCode === 9) {
					return;
				}
				break;
			case 38: // KEY_UP:
				this.moveUp();
				break;
			case 40: // KEY_DOWN:
				this.moveDown();
				break;
			default:
				return;
			}
			e.stopImmediatePropagation();
			e.preventDefault();
		},

		onKeyUp : function(e) {
			if (this.disabled) {
				return;
			}
			switch (e.keyCode) {
			case 38: // KEY_UP:
			case 40: // KEY_DOWN:
				return;
			}
			clearInterval(this.onChangeInterval);
			if (this.currentValue !== this.el.val()) {
				if (this.options.deferRequestBy > 0) {
					// Defer lookup in case when value changes very quickly:
					var me = this;
					this.onChangeInterval = setInterval(function() {
						me.onValueChange();
					}, this.options.deferRequestBy);
				} else {
					this.onValueChange();
				}
			}
		},

		onValueChange : function() {
			clearInterval(this.onChangeInterval);
			this.currentValue = this.el.val();
			var q = this.getQuery(this.currentValue);
			this.selectedIndex = -1;
			if (this.ignoreValueChange) {
				this.ignoreValueChange = false;
				return;
			}
			if (q === '' || q.length < this.options.minChars) {
				this.hide();
			} else {
				this.getSuggestions(q);
			}
		},

		getQuery : function(val) {
			var d, arr;
			d = this.options.delimiter;
			if (!d) {
				return $.trim(val);
			}
			arr = val.split(d);
			return $.trim(arr[arr.length - 1]);
		},

		getSuggestionsLocal : function(q) {
			var ret, arr, len, val, i;
			arr = this.options.lookup;
			len = arr.suggestions.length;
			ret = {
				suggestions : [],
				data : []
			};
			q = q.toLowerCase();
			for (i = 0; i < len; i++) {
				val = arr.suggestions[i];
				if (val.toLowerCase().indexOf(q) === 0) {
					ret.suggestions.push(val);
					ret.data.push(arr.data[i]);
				}
			}
			return ret;
		},

		getSuggestions : function(q) {
			var cr, me;
			//
			cr = this.isLocal ? this.getSuggestionsLocal(q)
					: this.cachedResponse[q];
			// 2012-07-19 遍历params，组装新的ajax_params
			var ajax_params = {};
			me = this;
			if (null != me.options.params) {
				// 遍历参数列表中的所有的属性
				// 由于jquery.each方法用的是回调函数，线程不安全，有时会导致this.isUseDefaultCache=false;设置不及时，而出现问题
				// $.each(me.options.params,function(name,value){
				// //如果属性值是jQuery对象，则将jQuery对象的val()设置到属性值中
				// if(null !=value && value instanceof jQuery){
				// ajax_params[name]=value.val();
				// //如果有jQuery对象作为查询对象，由于此对象的值是可以在界面上修改的，所有不能再使用缓存来查询
				// this.isUseDefaultCache=false;//不再使用默认的缓存
				// alert("value.val:"+value.val());
				// }else{
				// ajax_params[name]=value;
				// }
				// });

				var ps = me.options.params;
				for ( var p in ps) {
					// 如果属性值是jQuery对象，则将jQuery对象的val()设置到属性值中
					if (null != ps[p] && ps[p] instanceof jQuery) {
						ajax_params[p] = ps[p].val();
						// 如果有jQuery对象作为查询对象，由于此对象的值是可以在界面上修改的，所有不能再使用缓存来查询
						this.isUseDefaultCache = false;// 不再使用默认的缓存
						// alert("value.val:"+ps[p].val());
					} else {
						ajax_params[p] = ps[p];
					}
				}
			}// end if
			// alert("isUseDefaultCache:"+this.isUseDefaultCache);
			// 判断取数方式
			if (this.isUseDefaultCache && cr && $.isArray(cr.suggestions)) { // if
				// (cr
				// &&
				// $.isArray(cr.suggestions))
				// {
				// 从本地缓存中取
				// alert("local...");
				this.suggestions = cr.suggestions;
				this.data = cr.data;
				this.suggest();
			} else { // } else if (!this.isBadQuery(q)) {
				// 从数据库中查询
				// alert("query ....");
				//
				// me.options.params.query = q;
				ajax_params.query = q;
				// call action($.get to $.post)
				$.post(this.serviceUrl, ajax_params, function(txt) {
					me.processResponse(txt);
				}, "json");// 2012-06-03 old is text,update to json
			}
		},

		isBadQuery : function(q) {
			var i = this.badQueries.length;
			while (i--) {
				if (q.indexOf(this.badQueries[i]) === 0) {
					return true;
				}
			}
			return false;
		},

		hide : function() {
			this.enabled = false;
			this.selectedIndex = -1;
			this.container.hide();
		},

		suggest : function() {
			// alert(this.suggestions.length);
			if (this.suggestions.length === 0) {
				this.hide();
				return;
			}

			var me, len, div, f, v, i, s, mOver, mClick;
			me = this;
			len = this.suggestions.length;
			f = this.options.fnFormatResult;
			v = this.getQuery(this.currentValue);
			mOver = function(xi) {
				return function() {
					me.activate(xi);
				};
			};
			mClick = function(xi) {
				return function() {
					me.select(xi);
				};
			};
			this.container.hide().empty();
			for (i = 0; i < len; i++) {
				s = this.suggestions[i];
				div = $((me.selectedIndex === i ? '<div class="selected"'
						: '<div')
						+ ' title="'
						+ s
						+ '">'
						+ f(s, this.data[i], v)
						+ '</div>');
				div.mouseover(mOver(i));
				div.click(mClick(i));
				this.container.append(div);
			}
			this.enabled = true;
			this.container.show();
		},

		processResponse : function(text) {
			var response;
			try {
				// alert("text:" + text);
				response = eval("(" + text + ")");
			} catch (err) {
				return;
			}

			if (!$.isArray(response.data)) {
				response.data = [];
			}

			if (!this.options.noCache) {
				this.cachedResponse[response.query] = response;
				if (response.suggestions.length === 0) {
					this.badQueries.push(response.query);
				}
			}

			this.suggestions = response.suggestions;
			this.data = response.data;
			this.suggest();
		},

		activate : function(index) {
			var divs = null;
			var activeItem = null;
			divs = this.container.children();
			// Clear previous selection:
			if (this.selectedIndex !== -1 && divs.length > this.selectedIndex) {
				$(divs.get(this.selectedIndex)).removeClass();
			}
			this.selectedIndex = index;
			if (this.selectedIndex !== -1 && divs.length > this.selectedIndex) {
				activeItem = divs.get(this.selectedIndex);
				$(activeItem).addClass('selected');
			}
			return activeItem;
		},

		deactivate : function(div, index) {
			div.className = '';
			if (this.selectedIndex === index) {
				this.selectedIndex = -1;
			}
		},

		select : function(i) {
			var selectedValue, f;
			selectedValue = this.suggestions[i];
			if (selectedValue) {
				this.el.val(selectedValue);
				if (this.options.autoSubmit) {
					f = this.el.parents('form');
					if (f.length > 0) {
						f.get(0).submit();
					}
				}
				this.ignoreValueChange = true;
				this.hide();
				this.onSelect(i);
			}
		},

		moveUp : function() {
			if (this.selectedIndex === -1) {
				return;
			}
			if (this.selectedIndex === 0) {
				this.container.children().get(0).className = '';
				this.selectedIndex = -1;
				this.el.val(this.currentValue);
				return;
			}
			this.adjustScroll(this.selectedIndex - 1);
		},

		moveDown : function() {
			if (this.selectedIndex === (this.suggestions.length - 1)) {
				return;
			}
			this.adjustScroll(this.selectedIndex + 1);
		},

		adjustScroll : function(i) {
			var activeItem, offsetTop, upperBound, lowerBound;
			activeItem = this.activate(i);
			offsetTop = activeItem.offsetTop;
			upperBound = this.container.scrollTop();
			lowerBound = upperBound + this.options.maxHeight - 25;
			if (offsetTop < upperBound) {
				this.container.scrollTop(offsetTop);
			} else if (offsetTop > lowerBound) {
				this.container.scrollTop(offsetTop - this.options.maxHeight
						+ 25);
			}
			this.el.val(this.getValue(this.suggestions[i]));
		},

		onSelect : function(i) {
			var me, fn, s, d;
			me = this;
			fn = me.options.onSelect;
			s = $.trim(me.suggestions[i]); // 2012-07-19 add $.trim
			d = me.data[i];
			if (typeof d == "string") {
				d = $.trim(d);
			}
			// 2012-07-19 add $.trim 修改2012-12-27日，data为json对象时，加$.trim会添加双引号
			// add 2012-07-06 去掉suggestions设置的 "值_说明"的“值_部分”
			if (null != s) {
				var len = s.length;
				var index = s.indexOf("_");
				if (index >= 0 && len > index) {
					s = s.substring(index + 1, len);
				}
			}// end if

			//
			me.el.val(me.getValue(s));
			if ($.isFunction(fn)) {
				fn(s, d, me.el);
			}
		},

		getValue : function(value) {
			var del, currVal, arr, me;
			me = this;
			del = me.options.delimiter;
			if (!del) {
				return value;
			}
			currVal = me.currentValue;
			arr = currVal.split(del);
			if (arr.length === 1) {
				return value;
			}
			return currVal.substr(0, currVal.length
					- arr[arr.length - 1].length)
					+ value;
		}

	};

}(jQuery));
