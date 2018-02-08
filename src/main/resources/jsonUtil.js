/**
 * Created by yu on 2017/7/14.
 */


/**
 * 判断对象包含相似性,只判断一级结构
 * @param resp
 * @param obj
 * @constructor
 */
function SIMILAR(resp,obj){
    //把json打散成行,按行相似性做对比
    if(resp==null) return false
    if(JSON.stringify(resp).indexOf(JSON.stringify(obj))>-1)
        return true
    if(JSON.stringify(resp).indexOf(trimSp(JSON.stringify(obj)))>-1){
        return true
    }
    if(resp.constructor===String){
        return JSON.stringify(resp).indexOf(obj)>-1
    }
    var isHasKeyAll=true;
    for(var key in obj){
        var child=resp[key]
        if(child!=null && child.constructor==Object){

        }
        isHasKeyAll=isHasKeyAll && (child!=null)
    }

}
function find() {

}

/**
 * 判断对象是不是存在于另一个对象中
 * 如 {a:[b:c]} b 在工之中
 * 模糊验证,用于测试验证
 * @param arr
 * @param obj
 * @constructor
 */
function EXIST(arr,obj){

    if(arr.constructor===Array){
        for(var i=0;i<arr.length;i++){
            var curr=arr[i]
            if(JSON.stringify(curr)==JSON.stringify(obj))
                return true
        }

    }

}

/**
 *   模糊包含
 *   如[ {a:b},{c:d},{e:f,h:g}] 中包含了 {e:f}
 *   ojb必须是对象
 *   注:全局对象 CONTAIN_MSG
 *   _.matches() //跟lodash类似
 */
function CONTAIN(arr,obj) {
    if(arr==null) return false
    if(JSON.stringify(arr).indexOf(JSON.stringify(obj))>-1)
        return true
    if(JSON.stringify(arr).indexOf(trimSp(JSON.stringify(obj)))>-1){
        return true
    }
    if(arr.constructor===String){
        return JSON.stringify(arr).indexOf(obj)>-1
    }
    if(arr.constructor===Array){
        for(var i=0;i<arr.length;i++){
            var curr=arr[i]
            if((JSON.stringify(curr)+"").contain(trimSp( JSON.stringify(obj)))) return true
            if(CONTAIN(arr[i],obj)) return true
        }
    }
    if(arr.constructor===Object){
        if(inObject(arr,obj)) return true
        for(var key in arr){
            if(CONTAIN(arr[key],obj)) return true
        }
        for(var key in obj){
            if((JSON.stringify(arr[key])+"").contain(trimSp( JSON.stringify(obj)))) return true
            if(CONTAIN(arr[key],obj)) return true
        }
    }
    return false
}

/**
 *  从大json中定位取值,有一个json表达式
 *  如
 *   this({A:"1",b:"2",c:"3"},{A:"1",b:"?"})=>2
 *   this({A:"1",b:"2",c:"3"},{A:"1"})      =>this
 *   深度查找
 */
function LOCATE(big,obj){
    if(big==null) return null
    if(JSON.stringify(big).indexOf(JSON.stringify(obj))==0)
        return big
    if(big.constructor===String){
        return null
    }
    if(big.constructor===Array){
        for(var i=0;i<big.length;i++){
            var out=LOCATE(big[i],obj)
            if(out!=null) return out
        }
    }
    if(big.constructor===Object){
        if(inObject(big,obj)) return big
        for(var key in big){
            var out=LOCATE(big[key],obj)
            if(out!=null) return out
        }
    }
    return null
}


/**
 * 判断一层级的对象相似,
 * 如{a:b,c:b} 包含 {a:b} 为真
 */
function inObject(objBig,objTarget) {
    var match=true
    for(var key in objTarget){
        match=match&&(objTarget[key]==objBig[key]|| JSON.stringify(objTarget[key])==JSON.stringify(objBig[key]))
    }
    return match
}


String.prototype.startWith=function(str){
    return this.indexOf(str)==0
}
String.prototype.endWith=function(str){
    return this.indexOf(str)==this.length-str.length;
}
String.prototype.contain=function(str){
    return this.indexOf(str)>-1;
}
/**
 *  消除2端无用符号,如 {a} 变成 a
 *  用于json对比
 */
function  trimSp(str) {
    var arr=['{',']','}','[',"{[","]}","[{","}]","[[","]]","{{","}}"]
    for(var i=0;i<arr.length;i++){
        var ch=arr[i]
        if(str.startWith(ch))
            str=str.replace(ch,"");
        if(str.endWith(ch))
            str=str.replace(ch,"");
    }
    return str
}
/*
 var sk={"code":1,"msg":"OK","body":[{a:[{a:{"page":1,"size":10}}]}],"meta":{"aggregations":{"recommend":0,"interview":0,"offer":0,"entry":0,"onboard":0,"recommend10":0,"recommend11":0,"recommend12":0,"recommend13":0,"recommend14":0,"interview21":0,"interview22":0,"offer31":0,"offer32":0,"entry41":0,"entry42":0,"onboard51":0,"onboard52":0,"onboard53":0,"entry43":0,"offer33":0},"pagination":{"page":1,"size":10,"total":0,"totalPages":1}}}

 console.log( CONTAIN(   sk,{"recommend":0,"interview":0}))

 console.log( CONTAIN(   sk,{"recommend":0,"interview":0,"entry":0}))

 console.log( CONTAIN(   sk,{"recommend":0,"entry":0,"onboard":0}))
 console.log( CONTAIN(   sk,{"page":1,"size":10}))

 console.log( CONTAIN(   sk.meta,{"page":1,"size":10}))

 console.log( CONTAIN(   sk.body,{"page":1,"size":10}))

    var sk2={"code":1,"msg":"OK","body":[{a:[{a:{"page":1,"size":10,C:"bb"}}]}],"meta":{"aggregations":{"recommend":0,"interview":0,"offer":0,"entry":0,"onboard":0,"recommend10":0,"recommend11":0,"recommend12":0,"recommend13":0,"recommend14":0,"interview21":0,"interview22":0,"offer31":0,"offer32":0,"entry41":0,"entry42":0,"onboard51":0,"onboard52":0,"onboard53":0,"entry43":0,"offer33":0},"pagination":{"page":1,"size":10,"total":0,"totalPages":1}}}

    console.log( LOCATE(   sk2.body,{"page":1,"size":10}))
    console.log( LOCATE(   sk2,{"page":1,"size":10}))
    console.log( LOCATE(   sk2,{"offer":0,"entry":0}))
    console.log( LOCATE(   sk2,{"totalPages":1}))

*/