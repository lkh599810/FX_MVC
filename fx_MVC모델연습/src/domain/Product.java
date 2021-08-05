package domain;

public class Product {
	
	private int pno;
	private String pname;
	private String pcontents;	
	private int pprice;
	private int pstock;
	private String pcategory;
	private int pactivation;
	private int pquantity;
	private String pimage;
	
	// 1. 빈생성자 
		public Product() {}
		// 2. 모든 필드를 저장하는 생성자 
		public Product(int pno, String pname, String pcontents, int pprice, int pstock, String pcategory, int pactivation,
				int pquantity, String pimage) {
			this.pno = pno; 
			this.pname = pname;
			this.pcontents = pcontents;
			this.pprice = pprice;
			this.pstock = pstock;
			this.pcategory = pcategory;
			this.pactivation = pactivation;
			this.pquantity = pquantity;
			this.pimage = pimage;
		}
		// 3. 제품번호를 제외한 생성자 
		public Product( String pname, String pcontents, int pprice, int pstock, String pcategory, int pactivation,
				int pquantity, String pimage) {
			this.pname = pname;
			this.pcontents = pcontents;
			this.pprice = pprice;
			this.pstock = pstock;
			this.pcategory = pcategory;
			this.pactivation = pactivation;
			this.pquantity = pquantity;
			this.pimage = pimage;
		}
		
		// 4. get , set 메소드 
		public int getPno() {
			return pno;
		}

		public void setPno(int pno) {
			this.pno = pno;
		}

		public String getPname() {
			return pname;
		}

		public void setPname(String pname) {
			this.pname = pname;
		}

		public String getPcontents() {
			return pcontents;
		}

		public void setPcontents(String pcontents) {
			this.pcontents = pcontents;
		}

		public int getPprice() {
			return pprice;
		}

		public void setPprice(int pprice) {
			this.pprice = pprice;
		}

		public int getPstock() {
			return pstock;
		}

		public void setPstock(int pstock) {
			this.pstock = pstock;
		}

		public String getPcategory() {
			return pcategory;
		}

		public void setPcategory(String pcategory) {
			this.pcategory = pcategory;
		}

		public int getPactivation() {
			return pactivation;
		}

		public void setPactivation(int pactivation) {
			this.pactivation = pactivation;
		}

		public int getPquantity() {
			return pquantity;
		}

		public void setPquantity(int pquantity) {
			this.pquantity = pquantity;
		}

		public String getPimage() {
			return pimage;
		}

		public void setPimage(String pimage) {
			this.pimage = pimage;
		}

		
		
		
	
}
