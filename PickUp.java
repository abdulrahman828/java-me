import java.io.Serializable;

public class PickUp extends Vehicle  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int days;
      
	private final  static double COMISSION=22;
	
	public PickUp(String name, int id, double carCost, String color, String company,int days,double se, String n) throws InfoException {
		super(name,  id, carCost, color, company,n,se);
		this.days=days;
	}
	public PickUp(PickUp v) {
        super(v);
		this.days=v.days;
	}

	@Override
	public double CalcCost() {
		
			return (CarCost*days)+COMISSION+getInsuranceCost();
	}
	@Override
	public String toString() {
		return "PickUp "+super.toString()+ "Days=" + days                                    +"\n"+"-----------------------------------------";
	}
	public static double getComission() {
		return COMISSION;
	}

}
