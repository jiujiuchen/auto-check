    jQuery.extend( {  
    min : 1,  
    reg : function(x) {  
        return new RegExp("^[0-9]*[1-9][0-9]*$").test(x);  
    },  
    amount : function(obj, mode) {  
        var x = jQuery(obj).val();  
        if (this.reg(parseInt(x))) {  
            if (mode) {  
                x++;  
            } else {  
                x--;  
            }  
        } else {  
            jQuery(obj).val(1);  
            jQuery(obj).focus();  
        }  
        return x;  
    },  
    reduce : function(obj) {  
        var x = this.amount(obj, false);  
        if (parseInt(x) >= this.min) {  
            jQuery(obj).val(x);  
        } else {  
            jQuery(obj).val(1);  
            jQuery(obj).focus();  
        }  
    },  
    add : function(obj,max) {  
        var x = this.amount(obj, true);  
        //var max = jQuery('#nAmount').val();  
        if (parseInt(x) <= parseInt(max)) {  
            jQuery(obj).val(x);  
        } else {  
            jQuery(obj).val(max == 0 ? 1 : max);  
            jQuery(obj).focus();  
        }  
    },  
    modify : function(obj,max) {  
        var x = jQuery(obj).val();  
        //var max = jQuery('#nAmount').val();  
        if (!this.reg(x)) {  
            jQuery(obj).val(1);  
            jQuery(obj).focus();  
            return;  
        }  
        var intx = parseInt(x);  
        var intmax = parseInt(max);  
        if (intx < this.min) {  
            jQuery(obj).val(this.min);  
            jQuery(obj).focus();  
        } else if (intx > intmax) {  
            jQuery(obj).val(max == 0 ? 1 : max);  
            jQuery(obj).focus();  
        }  
    }  
}); 