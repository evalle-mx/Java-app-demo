package netto.app.progfuncional.stream;
/* https://mkyong.com/java8/java-8-stream-reduce-examples/ */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class JavaMapReduce {

	
	public static void main(String[] args) {
		JavaMapReduce jmr = new JavaMapReduce();
		jmr.demo();
	}
	
	private Function<Invoice, BigDecimal> getSubtotal = x -> x.getQty().multiply(x.getPrice());
	
	public void demo() {
		List<Invoice> invoices = Arrays.asList(
                new Invoice("A01", BigDecimal.valueOf(9.99), BigDecimal.valueOf(1)),
                new Invoice("A02", BigDecimal.valueOf(19.99), BigDecimal.valueOf(1.5)),
                new Invoice("A03", BigDecimal.valueOf(4.99), BigDecimal.valueOf(2))
        );
		
		BigDecimal sum = invoices.stream()
                .map(
                		getSubtotal
                	)    // map
                .reduce(BigDecimal.ZERO, BigDecimal::add);      // reduce

        System.out.println("Real Sum: " + sum);    // 49.955
        System.out.println("Rounded Sum: " +  sum.setScale(2, RoundingMode.HALF_UP));  // 49.96
	}
	
	class Invoice {

	    String invoiceNo;
	    BigDecimal price;
	    BigDecimal qty;
	    
	    public Invoice(String invoiceNo, BigDecimal price, BigDecimal qty) {
			this.invoiceNo= invoiceNo;
			this.price = price;
			this.qty = qty;
		}
	    
		public String getInvoiceNo() {
			return invoiceNo;
		}
		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public BigDecimal getQty() {
			return qty;
		}
		public void setQty(BigDecimal qty) {
			this.qty = qty;
		}

	    // getters, stters n constructor
	}
}
