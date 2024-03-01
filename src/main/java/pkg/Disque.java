package pkg;

//1. Define a class to model the object:
class Disque {
 private String ordre;
 private String prix;

 // 2. Create a constructor to initialize the object's properties:
 public Disque(String ordre, String prix) {
     this.ordre = ordre;
     this.prix = prix;
 }

 // 3. Provide getter and setter methods to access and modify the properties:
 public String getOrdre() {
     return ordre;
 }

 public void setOrdre(String ordre) {
     this.ordre = ordre;
 }

 public String getPrix() {
     return prix;
 }

 public void setPrix(String prix) {
     this.prix = prix;
 }
}

//4. Instantiate an object of the Disque class:
// Replace ordreValue and prixValue with actual values
