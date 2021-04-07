package smbms.c2;

import smbms.c1.Father;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-04 09:17</p>
 **/
public class Child extends Father {

    private String address="子类的address";

    public void test4() {
        super.test4();
    }

    public static void main(String[] args) {



        Child child = new Child();

        System.out.println(child.address);

        System.out.println(child.info);


    }

}
