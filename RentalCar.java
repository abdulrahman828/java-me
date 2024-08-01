import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;



	public class RentalCar {

	    private String name;
	    private Vehicle[] arrayOfvehicles;
	    private int counterOfArrayofV;
         
	    public RentalCar(String name, int size) {
	        this.name = name;
	        arrayOfvehicles = new Vehicle[size];
	        counterOfArrayofV = 0;
	    }


public int search(int id) {
	for(int i=0;i<counterOfArrayofV;i++)
		if( arrayOfvehicles[i].getId()==id)
		return i;
		
		
		return -1;
	
}
public boolean AddV(Vehicle v) {
	if(search(v.getId())!=-1 || counterOfArrayofV>=arrayOfvehicles.length) 
		return false;
	
	
	
	else {
   if(v instanceof SUV)	{
	   arrayOfvehicles[counterOfArrayofV]=new SUV((SUV)v);
	   counterOfArrayofV++;
   }
   else if(v instanceof Sedan)	{
	   arrayOfvehicles[counterOfArrayofV]=new Sedan((Sedan)v);
	   counterOfArrayofV++;
   }	
   else if(v instanceof PickUp)	{
	   arrayOfvehicles[counterOfArrayofV]=new PickUp((PickUp)v);
	   counterOfArrayofV++;
	   
   }	
   
   return true;}
   
	}
public boolean RemoveV(Vehicle v) {
	int index=search(v.getId());

if(index==-1) 
	
	return false;
	

arrayOfvehicles[index]=arrayOfvehicles[counterOfArrayofV-1];
arrayOfvehicles[counterOfArrayofV-1]=null;
counterOfArrayofV--;

return true;
	

}

public String toString() {
	String s= ""+ name +"\n ";
	for(int i=0;i<counterOfArrayofV;i++) {
		
		s+=arrayOfvehicles[i].toString()+ "\n,CalcCost:"+arrayOfvehicles[i].CalcCost()+"\n";
		
		
	}
	
	return s;
}




public double TotalCostAllCars() {
	double total=0;
	for(int i=0;i<counterOfArrayofV;i++) 
     total+= arrayOfvehicles[i].CalcCost();
	
	
	return total;

}

public void saveVehicleToFile(String filename) throws IOException {
   File outFile=new File(filename);
   FileOutputStream fos = new FileOutputStream(outFile);
  ObjectOutputStream object = new ObjectOutputStream(fos);
  try {   
		   for(int i=0;i<counterOfArrayofV;i++) 
    	   object.writeObject((arrayOfvehicles[i]));
  }finally {
	  if(object!=null)
		  object.close();
	  if(fos!=null)
		  fos.close();
  }
}
public void readVehiclesFromFile( String filename) throws IOException {
    ObjectInputStream ois = null;
    
    try {
        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
        while (true) {
            try {
            	
                Vehicle p=(Vehicle)ois.readObject();
                System.out.println(p.toString()) ;
               
            } catch (EOFException eof) {
                break;
            } catch (ClassNotFoundException eom) {
                System.out.println("Class not found: " + eom.getMessage());
            }
        }
        System.out.println("End of reading");
    } finally {
        if (ois != null) {
            ois.close();
        }
    }
}


}
