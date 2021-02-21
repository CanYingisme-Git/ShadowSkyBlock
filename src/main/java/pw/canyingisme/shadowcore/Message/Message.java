package pw.canyingisme.shadowcore.Message;

public class Message {
    public String EnableMessage(){
        return "加载ShadowCore";
    }
    public String disableMessage(){
        return "卸载ShadowCore";
    }
    public String reflectError(String reflectType){
        return "Error 反射"+reflectType+"失败";
    }
}
