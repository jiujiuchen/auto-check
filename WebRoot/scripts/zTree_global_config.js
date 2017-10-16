/**
 * zTree全局配置  
 */
var zTree;//zTree控件全局对象，调用zTreeInit后可直接使用
var clickExecFunc;//单击树节点需要执行的方法全局变量--请勿为该变量赋值
var expandExecFunc;//单击树节点左侧的+时，触发的事件--请勿为该变量赋值
var nodeId;//当前选中树节点的id
var nodeName;//当前选中树节点的名称
var preNodeId;//当前选中树节点的上一级id
var nextNodeId;//当前选中树节点的下一级id
var parentNodeId;//当前选中树节点的父级id
var bizType;//当前选中的节点所属的业务功能标识
var nodeIcon;//节点定义的图标
var iconPath = ctx+"/css/zTreeStyle/img/icons/";//存放zTree节点图标的路径常量

var setting = {   
	    isSimpleData : false,            //数据是否采用简单 Array 格式，默认false   
	    treeNodeKey : "id",              //在isSimpleData格式下，当前节点id属性   
	    treeNodeParentKey : "upId",  //在isSimpleData格式下，当前节点的父节点id属性   
	    showLine : true,                  //是否显示节点间的连线   
	    checkable : true                 //每个节点上是否显示 CheckBox   
};

/**
 * 鼠标左键单击树节点和单击节点前加号所执行的动作
 * @param yourClickFunction 你自己定义的方法，该方法就是你希望在单击树节点后所执行的工作
 * @param yourExpandFunction 你自己定义的方法，该方法就是你希望在单击节点前加号所执行的工作
 */
function regFunction(yourClickFunction,yourExpandFunction){
	clickExecFunc = yourClickFunction;
	expandExecFunc = yourExpandFunction;
	setting = {
		    callback:{
		    	onClick:treeNodeOnClick,
		    	beforeExpand: beforeExpand,
		    	onExpand:treeNodeOnExpand
		    }
	}
}

/**
 * 展开前触发事件 
 * @param treeId
 * @param treeNode
 */
function beforeExpand(treeId, treeNode) {
	nodeIcon = treeNode.icon;
	if (!treeNode.isAjaxing) {
		treeNode.icon =ctx+"/css/zTreeStyle/img/loading.gif";
		zTree.updateNode(treeNode);
		return true;
	} else {
		alert("zTree 正在下载数据中，请稍后展开节点。。。");
		return false;
	}
}

/**
 * 获取节点ID为nodeId值的节点对象
 * @returns
 */
function getCurNode(){
	var node = zTree.getNodeByParam("id",nodeId, null);
	return node;
}

/**初始化树控件并获取zTree核心对象
 * 
 * @param domTreeId 树控件呈现的DOM元素ID
 * @param zTreeDataSource 树控件的数据源：Json格式
 */
function zTreeInit(domTreeId,zTreeDataSource){
	 $.fn.zTree.init($("#"+domTreeId), setting,zTreeDataSource);
	 zTree = $.fn.zTree.getZTreeObj(domTreeId);
}

/**
 * 单击节点前加号后，自动装配当前选中节点的上一级节点ID、下一级节点ID、父级ID
 * 单击节点的私有方法，请勿调用此方法，应使用regFunction(yourClickFunction,yourExpandFunction)
 * @param e
 * @param treeId
 * @param node
 */
function treeNodeOnExpand(e, treeId, node) {
	bizType = node.bizType;
	nodeId = node.id;
	nodeName = node.name;
	var nodes = zTree.getNodesByParam("id", nodeId, null);
	var cnode = nodes[0];
	var nextNode = cnode.getNextNode();
	if(nextNode){
		nextNodeId = nextNode.id;
	}else{
		nextNodeId = "empty";
	}
	var preNode = cnode.getPreNode();
	if(preNode){
		preNodeId = preNode.id;
	}else{
		preNodeId = "empty";
	}
	var parentNode = node.getParentNode();
	if(parentNode){
		parentNodeId = parentNode.id;
	}else{
		parentNodeId = "empty";
	}
	
	if(node.firstExpand == true){	
		if(expandExecFunc){
			expandExecFunc();
		}
		node.firstExpand = false;
	}
	node.icon = nodeIcon;
	zTree.updateNode(node);
	
//	if(typeof(cnode.children)=="undefined"){
//		cnode.isParent = false;		
//	}else{
//		cnode.isParent = true;		
//	}
//	zTree.updateNode(cnode);
}

/**
 * 单击树节点后，自动装配当前选中节点的上一级节点ID、下一级节点ID、父级ID
 * 单击树节点的私有方法，请勿调用此方法，应使用regFunction(yourClickFunction,yourExpandFunction)
 * @param e
 * @param treeId
 * @param node
 */
function treeNodeOnClick(e, treeId, node){
	bizType = node.bizType;
	nodeId = node.id;
	nodeName = node.name;
	var nodes = zTree.getNodesByParam("id", nodeId, null);
	var cnode = nodes[0];
	var nextNode = cnode.getNextNode();
	if(nextNode){
		nextNodeId = nextNode.id;
	}else{
		nextNodeId = "empty";
	}
	var preNode = cnode.getPreNode();
	if(preNode){
		preNodeId = preNode.id;
	}else{
		preNodeId = "empty";
	}
	var parentNode = node.getParentNode();
	if(parentNode){
		parentNodeId = parentNode.id;
	}else{
		parentNodeId = "empty";
	}
	if(clickExecFunc){
		clickExecFunc();	  
	}	
}

/**
 *删除节点后，重新选中其它节点
 */
function deleteTreeNode(nodeId) {
	var treeObj = zTree;
	var nodes = treeObj.getNodesByParam("id", nodeId, null);
	var node = nodes[0];
	if(typeof(node) == 'undefined'){
		alert("no node by selected");
		return;
	}
	var nextNode = node.getNextNode();
	if (nextNode == null) {
		//没有下一个节点，读取上一个节点
		nextNode = node.getPreNode();
	}
	if (nextNode == null) {
		//上一个节点也不存在，取父节点
		nextNode = node.getParentNode();
	}
	treeObj.removeNode(node);
	treeObj.selectNode(nextNode);
	treeNodeOnClick(null, null, nextNode);
}

/**
 * 在当前选中的节点下级或同级添加节点 
 * @param newNodeId 新节点ID
 * @param newNodeName 新节点名称
 * @param newNodeOpenIcon 新节点展开时的图标，
 * @param newNodeCloseIcon 新节点关闭时的图标
 * @param isSameLevel true是同级，false是下级
 * @param bizTypeName 新增节点所属的业务功能标识
 * @param isSelectedAddNode 添加节点之后，是否要选中该节点,如果true则选中并触发点击该节点事件
 * @param isParentNode 是否是父节点，true则节点左侧带+号，false则是叶子节点
 * @param addToNodeId 新增至指定节点的节点ID
 * 								        如果传入此参数，则isSameLevel默认为false,即添加至其下级
 *                                     缺省则添加到当前选中的节点
 */
function addNode(newNodeId,newNodeName,newNodeOpenIcon,newNodeCloseIcon,isSameLevel,bizTypeName,isSelectedAddNode,isParentNode,addToNodeId){	
	var resultNode;
	var currentNode;
	//构建待新增的节点
	var newNode = null;
	if(newNodeOpenIcon != null && newNodeCloseIcon == null){
		newNode = {id:newNodeId, pId:null, isParent:isParentNode, name:newNodeName,bizType:bizTypeName,firstExpand:true,icon:newNodeOpenIcon};	
	}else{
		newNode = {id:newNodeId, pId:null, isParent:isParentNode, name:newNodeName,bizType:bizTypeName,firstExpand:true,iconOpen:newNodeOpenIcon,iconClose:newNodeCloseIcon};
	}
	
	//传入想添加到的节点ID
	if(addToNodeId){
		currentNode = zTree.getNodeByParam("id",addToNodeId,null);
		newNode.pId = addToNodeId;
		zTree.addNodes(currentNode, newNode);
	}else{
		currentNode = zTree.getSelectedNodes()[0];
		if(typeof(currentNode) == "undefined"){
			alert("no node was selected,can not add!");
			return null;
		}
		if(isSameLevel){
			parentNode = currentNode.getParentNode();
			if(parentNode){
				newNode.pId = parentNode.id;
				zTree.addNodes(parentNode, newNode);
			}else{
				zTree.addNodes(null, newNode);
			}
		}else{
			newNode.pId = currentNode.id;
			zTree.addNodes(currentNode, newNode);
		}
	}

	currentNode.isParent = true;	
	zTree.updateNode(currentNode);
	
	resultNode = zTree.getNodeByParam("id",newNode.id,null);
	
	if(isSelectedAddNode){
		zTree.selectNode(resultNode,false);
		treeNodeOnClick(null, null, resultNode);
	}
	return resultNode;
}

/**
 * 修改节点的名称
 * @param modifyNodeId 
 *                要修改的节点ID，缺省或NULL则更新选中节点；传入节点ID直接更新指定节点
 * @param newName
 * 				   修改后的名称
 */
function modifyNode(modifyNodeId,newName){
	var currentNode;
	if(modifyNodeId == null){
		currentNode = zTree.getSelectedNodes()[0];
	}else if(modifyNodeId != null && newName == null){
		currentNode = zTree.getSelectedNodes()[0];
		newName = modifyNodeId;
	}else{
		currentNode = zTree.getNodesByParam("id", modifyNodeId, null)[0];
	}
	if(typeof(currentNode) == "undefined"){
		alert("no node was selected,can not modify!");
		return;
	}
	currentNode.name = newName;
	zTree.updateNode(currentNode);
}

/**
 * 移动指定ID的节点到目标节点
 * @param targetNodeId 目标节点的ID
 * @param moveNodeId 需要移动的节点ID 
 * @param moveType 移动的类型，可选值:"inner"、"prev","next"
 * 				   inner:移动到目标节点，作为其子节点
 * 		           prev:移动到目标节点，作为其同级节点，并且在目标节点的上面
 * 				   next:移动到目标节点，作为其同级节点，并且在目标节点的下面
 */
function moveTreeNode(targetNodeId,moveNodeId,moveType){
    var targetNode = zTree.getNodeByParam("id", targetNodeId, null);
    var moveNode  = zTree.getNodeByParam("id",moveNodeId,null);
    if(!targetNode){
    	alert("target node is not exist !");
    	return null;
    }
    if(!moveNode){
    	alert("move node is not exist !");
    	return null;
    }
    
    var resultNode = zTree.moveNode(targetNode,moveNode,moveType);
    if(resultNode == null){
    	alert("移动失败,目标节点和要移动的节点已经是父子关系或试图将两个节点的父子关系互换");
    	return null;
    }else{
    	zTree.selectNode(resultNode,false);
    	treeNodeOnClick(null, null, resultNode);
    	return resultNode;
    }
}

/**
 * 异步加载之后，新增节点
 */
function asyncAddNode(){
	$.each(ajaxResult,function(){
		if(this.icon!=""){
			addNode(this.id,this.name,this.icon,null,false,this.bizType,false,this.isParent,nodeId);
		}else{
			addNode(this.id,this.name,this.iconOpen,this.iconClose,false,this.bizType,false,this.isParent,nodeId);	
		}		
	});
}

