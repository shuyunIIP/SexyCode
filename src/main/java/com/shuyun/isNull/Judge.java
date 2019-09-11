package com.shuyun.isNull;

import sun.rmi.runtime.Log;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @ClassName Judge
 * @Description TODO 优雅判空
 * @Author Shuyun
 * @Date 2019/9/11 16:13
 **/
public class Judge {
    public static void main(String[] args) {
        /**
         * 臃肿写法
         */
        User fatUser=User.getUser();
        if(fatUser==null)
            fatUser=new User("韩梅梅");
        System.out.println(fatUser.toString());

        /**
         * 优雅写法
         */
  //      try {
 //           User sexUser = Optional.ofNullable(User.getUser()).orElseThrow(new ObjectNotFoundException("找不到用户"));
            User sexUser=Optional.ofNullable(User.getUser()).orElse(new User("韩梅梅"));
            System.out.println(sexUser.toString());
//        }catch (Exception e){
//            System.out.println(e);
//        }

//        System.out.println(success());

    }

    public static boolean success(){
        boolean result=false;

        try {
            User sexUser = Optional.ofNullable(User.getUser()).orElseThrow(new ObjectNotFoundException("找不到用户"));
//        User sexUser=Optional.ofNullable(User.getUser()).orElse(new User("韩梅梅"));
            result=true;
            System.out.println(sexUser.toString());
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
}


class User{
    private String name;
    public User(String name){
        this.name=name;
    }

    public static User getUser(){
        int random=new Random().nextInt(3);
        if(random>1)
            return new User("李雷");
        else
            return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "你好哇，"+this.name+"!";
    }
}

class ObjectNotFoundException  implements Supplier{
    private String message;

    public ObjectNotFoundException(String message){
        this.message=message;
    }

    @Override
    public Object get() {
        return new RuntimeException(this.message);
    }
}