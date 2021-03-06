package com.gugu.guguuser.util;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class SerialUtil {

    private ArrayList<Byte> serialList;

    public ArrayList<Byte> getSerialList() {
        return serialList;
    }

    public void setSerialList(ArrayList<Byte> serialList) {
        this.serialList = serialList;
    }

    public Byte calcuSerial(){
        if(serialList.size()==0){
            return 1;
        }
        else{
            for(Byte i=0;i<serialList.size();i++){
                Integer tempInt=i+1;
                Byte tempb=Byte.parseByte(tempInt.toString());
                if(!serialList.get(i).equals(tempb)){
                    Integer temp=i+1;
                    return Byte.parseByte(temp.toString());
                }
            }
        }
        Integer re=serialList.size()+1;
        return Byte.parseByte(re.toString());
    }

}
