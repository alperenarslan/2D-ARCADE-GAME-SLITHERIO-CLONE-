package items;

public class ItemFactory {
	
	public Item getShape(String shapeType){
	      if(shapeType == null){
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("Poison")){
	         return new Poison(0, 0);
	         
	      } else if(shapeType.equalsIgnoreCase("Food")){
	         return new Food(0, 0, 0, null);
	         
	      } else if(shapeType.equalsIgnoreCase("PowerUp")){
	         return new PowerUp(0, 0);
	      } else if(shapeType.equalsIgnoreCase("PowerDown")){
		         return new PowerDown(0, 0);
		      }
	      
	      return null;
	   }

}
