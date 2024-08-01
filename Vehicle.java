import java.io.Serializable;

public abstract class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;
protected String Color;
protected int id;
protected double CarCost;
protected String name;
protected String Company;
protected String insuranceType;
protected double insuranceCost;


 


public Vehicle(String name, int id , double carCost, String color, String company,  String insuranceType ,double insuranceCost) throws InfoException {
	if(id<0 || carCost<0 ||insuranceCost<0||name==null ||insuranceType==null)
		throw new InfoException("invalid data ");
	
	this.name = name;
	this.id=id;
	CarCost = carCost;
	Color = color;
	Company = company;
	this.insuranceType=insuranceType;
	this.insuranceCost=insuranceCost;
			
	
	
	
	
	
   
}
public Vehicle(Vehicle v) {
	
	Color = v.Color;
id=v.id;
	CarCost = v.CarCost;
	this.name = v.name;
	Company = v.Company;
	this.insuranceCost=v.insuranceCost;
	this.insuranceType=v.insuranceType;
	}

public String toString() {
	return "\n"
			+ "----------------------------------------------------------"+"\n"
			 +"id="+id                                                    +"\n"
			 +"name: "+name                                             +"\n" 
	         +"company "+Company                                       +"\n" 
		  + "Color:"+Color                                              +"\n" 
		   +"InsuranceType "+insuranceType                               +"\n"
		  +"insurnaceCost "+insuranceCost                              +"\n" 
		                                                        ;
		    
			                                                        
			
}
public String getInsuranceType() {
	return insuranceType;
}
public void setInsuranceType(String insuranceType) {
	this.insuranceType = insuranceType;
}
public double getInsuranceCost() {
	return insuranceCost;
}
public void setInsuranceCost(double insuranceCost) {
	this.insuranceCost = insuranceCost;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getColor() {
	return Color;
}
public void  setColor(String color) {
	Color = color;
}

public double getCarCost() {
	return CarCost;
}
public void setCarCost(double CarCost) {
	this.CarCost=CarCost;
}
public String getCompany() {
	return Company;
}
public void setCompany(String company) {
	Company = company;
}
public abstract double CalcCost();
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
	




}
