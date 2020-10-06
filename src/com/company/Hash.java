package com.company;

import java.util.LinkedList;

public class Hash {

    Element[] hashTable;
    int collisionAmm=0;
    int[] table;

    Hash() {
        hashTable = new Element[2800];
        for (int i=0;i<2800;i++) hashTable[i]=new Element();
        table= new int[]{
                43, 119, 224, 71, 180, 142, 42, 160, 104, 48, 247, 103, 15, 11, 138, 239,
                98, 6, 150, 85, 36, 23, 112, 164, 135, 207, 169, 5, 26, 64, 165, 219,
                61, 20, 68, 89, 130, 63, 52, 102, 24, 229, 132, 245, 80, 216, 195, 115,
                90, 168, 156, 203, 177, 120, 2, 190, 188, 7, 100, 185, 174, 243, 162, 10,
                59, 153, 29, 9, 213, 167, 84, 93, 30, 46, 94, 75, 151, 114, 73, 222,
                197, 96, 210, 45, 16, 227, 248, 202, 51, 152, 252, 125, 81, 206, 215, 186,
                39, 158, 178, 187, 131, 136, 1, 49, 50, 17, 235, 91, 47, 129, 60, 99,
                123, 251, 67, 250, 161, 8, 107, 97, 241, 111, 181, 82, 249, 33, 69, 55,
                237, 18, 253, 225, 208, 3, 172, 244, 255, 126, 101, 79, 145, 141, 228, 121,
                133, 232, 196, 144, 198, 124, 53, 4, 108, 74, 223, 234, 134, 230, 157, 139,
                189, 205, 199, 128, 176, 19, 211, 236, 127, 192, 231, 70, 233, 88, 146, 44,
                183, 201, 22, 83, 13, 214, 116, 109, 159, 32, 95, 226, 140, 220, 57, 12,
                154, 35, 86, 171, 105, 34, 38, 200, 147, 58, 77, 118, 173, 246, 76, 254,
                221, 31, 209, 182, 143, 92, 149, 184, 148, 62, 113, 65, 37, 27, 106, 166,
                0, 14, 204, 72, 21, 41, 56, 66, 28, 193, 40, 217, 25, 54, 179, 117,
                238, 87, 240, 155, 122, 170, 242, 212, 191, 163, 78, 218, 137, 194, 175, 110
        };
    }

    void put(String key, String val) {
        int ind = hashFunc(key);
        if (hasKey(key)) {
            getValue(key, ind).setValue(val);
        } else {
            if (hashTable[ind].getKey() == null) {
                hashTable[ind].setValue(val);
                hashTable[ind].setKey(key);
            } else {
                Element el = hashTable[ind];
                System.out.println("\n"+el);
                while (el.getNext() != null) {
                    System.out.println("\n"+el);
                    el = el.getNext();

                }
                el.setNext(new Element(key, val));
                collisionAmm++;
                System.out.println(key);
                System.out.println("\nКоллизия\n");
            }
        }
    }

    boolean remove(String key) {
        if (hasKey(key)) {
            int ind = hashFunc(key);
            if (key.equals(hashTable[ind].getKey())) {
                hashTable[ind].setKey(null);
                hashTable[ind].setValue(null);
            } else {
                Element befor = hashTable[ind];
                while (key.equals(befor.getNext().getKey()))
                    befor = befor.getNext();
                befor.setNext(befor.getNext().getNext());
                collisionAmm--;
            }
            return true;
        }
        return false;
    }

    LinkedList<Element> showAll() {
        LinkedList<Element> list=new LinkedList();
        for (Element x : hashTable) {
            if (x.getKey()!=null){
                while(x!=null){
                    list.add(x);
                    x=x.getNext();
                }
            }
        }
        return list;
    }


    private int hashFunc(String key) {
        int hh=0;
        for (int j = 0; j < 2; ++j) {
            int h = table[(key.charAt(0) + j) % 256];
            for (int i = 1; i < key.length(); ++i) {
                h = table[(h ^ key.charAt(i))%256];
            }
            hh = hh*10+h;
        }
        return hh;
    }

    boolean hasKey(String key) {
        int ind = hashFunc(key);
        int deep = 0;
        Element elem = hashTable[ind];
        while (elem != null){
            if (key.equals(elem.getKey()))
                return true;
            elem = elem.getNext();
        }
        return false;
    }

    Element getValue(String key, int ind) {
        Element elem = hashTable[ind];
        while (!key.equals(elem.getKey()))
            elem = elem.getNext();
        return elem;
    }

    Element getValue(String key) {
        Element elem = hashTable[hashFunc(key)];
        while (!key.equals(elem.getKey()))
            elem = elem.getNext();
        return elem;
    }
}

class Element {
    String key;
    String value;
    Element next;

    Element() {
        key = null;
        value = null;
        next = null;
    }

    Element(String k, String v) {
        key = k;
        value = v;
        next = null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Element getNext() {
        return next;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public String toString(){
        return key;
    }
}