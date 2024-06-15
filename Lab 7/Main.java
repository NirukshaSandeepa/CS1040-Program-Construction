import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

class ItemCodeNotFound extends Exception {
    public ItemCodeNotFound() {
        super("Item not found in database.");
    }
}

class GroceryItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String itemName;
    private float price;
    private String manufacturerName;
    private float weight;
    private Date manufacturingDate;
    private Date expiryDate;
    private int discountPercentage;

    public GroceryItem(String itemName, float price, String manufacturerName, float weight,
            Date manufacturingDate, Date expiryDate, int discountPercentage) {
        this.itemName = itemName;
        this.price = price;
        this.manufacturerName = manufacturerName;
        this.weight = weight;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.discountPercentage = discountPercentage;
    }

    public String getItemName() {
        return itemName;
    }

    public float getPrice() {
        return price;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public float getWeight() {
        return weight;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}

class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cashierName;
    private String branch;
    private String customerName;
    private List<GroceryItem> itemList;
    private float totalDiscount;
    private float totalPrice;
    private LocalDateTime dateTime;

    public Bill(String cashierName, String branch, String customerName, List<GroceryItem> itemList,
            float totalDiscount, float totalPrice) {
        this.cashierName = cashierName;
        this.branch = branch;
        this.customerName = customerName;
        this.itemList = itemList;
        this.totalDiscount = totalDiscount;
        this.totalPrice = totalPrice;
        this.dateTime = LocalDateTime.now();
    }

    public String getCashierName() {
        return cashierName;
    }

    public String getBranch() {
        return branch;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<GroceryItem> getItemList() {
        return itemList;
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

class POS implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, GroceryItem> database;
    private List<Bill> pendingBills;

    public POS() {
        // Initialize the database
        database = new HashMap<>();

        GroceryItem item1 = new GroceryItem("Apple", 0.75f, "ABC Inc.", 0.2f,
                new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2022, Calendar.DECEMBER, 31).getTime(), 10);
        GroceryItem item2 = new GroceryItem("Banana", 0.5f, "DEF Inc.", 0.15f,
                new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2022, Calendar.DECEMBER, 31).getTime(), 20);
        GroceryItem item3 = new GroceryItem("Milk", 3f, "GHI Inc.", 1.5f,
                new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2022, Calendar.DECEMBER, 31).getTime(), 5);

        database.put("1001", item1);
        database.put("1002", item2);
        database.put("1003", item3);

        // Initialize the pending bills list
        pendingBills = new ArrayList<>();
    }

    public void printPendingBills() {
        System.out.println("Pending bills:");
        for (Bill bill : pendingBills) {
            System.out.println(bill.getCashierName() + "\t" + bill.getBranch() + "\t" + bill.getCustomerName() + "\t"
                    + bill.getTotalPrice() + "\t" + bill.getTotalDiscount() + "\t" + bill.getDateTime());
        }
    }

    public GroceryItem getItemDetails() throws IOException, ItemCodeNotFound {
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String item_code = br.readLine();

        if (!database.containsKey(item_code)) {
            throw new ItemCodeNotFound();
        }

        br.close();
        r.close();

        return database.get(item_code);
    }

    public void generateBill(String cashierName, String branch, String customerName, List<String> itemList)
    throws ItemCodeNotFound {
    List<GroceryItem> groceryItems = new ArrayList<>();
    float totalDiscount = 0;
    float totalPrice = 0;

    for (String itemCode : itemList) {
        GroceryItem item = null;
        try {
            item = getItemDetails();
        } catch (IOException e) {
            System.out.println("Error fetching item details: " + e.getMessage());
        }

        if (item == null) {
            continue; // Skip adding invalid items
        }

        groceryItems.add(item);
        totalDiscount += (item.getPrice() * item.getDiscountPercentage() / 100.0f);
        totalPrice += item.getPrice();
    }

    float netPrice = totalPrice - (totalPrice * totalDiscount / 100.0f);

    Bill bill = new Bill(cashierName, branch, customerName, groceryItems, totalDiscount, netPrice);
    pendingBills.add(bill);

    System.out.println("Bill generated:");
    System.out.println("Cashier: " + cashierName);
    System.out.println("Branch: " + branch);
    System.out.println("Customer: " + customerName);
    System.out.println("Items:");

    for (GroceryItem item : groceryItems) {
        System.out.println(item.getItemName() + "\t" + item.getPrice() + "\t" + item.getWeight() + "\t"
                + item.getDiscountPercentage() + "\t" + (item.getPrice() * item.getWeight()) + "\t"
                + (item.getPrice() * item.getDiscountPercentage() / 100.0f * item.getWeight()));
    }

    System.out.println("Total discount: " + totalDiscount);
    System.out.println("Total price: " + totalPrice);
    System.out.println("Net price: " + netPrice);
    System.out.println("Date and time: " + bill.getDateTime());
    }
}

public class Main {
    public static void main(String[] args) {
        POS pos = new POS();

        // Example usage: generate a bill for a customer
        List<String> itemList = new ArrayList<>();
        itemList.add("1001"); // Apple
        itemList.add("1003"); // Milk
        try {
            pos.generateBill("John Doe", "Branch A", "Alice", itemList);
        } catch (ItemCodeNotFound e) {
            System.out.println("Error generating bill: " + e.getMessage());
        }

        // Example usage: print pending bills
        pos.printPendingBills();
    }
}