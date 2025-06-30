package com.jocata.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jocata.data.transaction.InvoiceDao;
import com.jocata.datamodel.product.form.ProductForm;
import com.jocata.datamodel.transaction.entity.Invoice;
import com.jocata.datamodel.transaction.form.InvoiceForm;
import com.jocata.datamodel.user.form.UserForm;
import com.jocata.response.OrderItemForm;
import com.jocata.response.OrderResponseForm;
import com.jocata.service.InvoiceService;
import com.jocata.service.OrderAPIService;
import com.jocata.service.ProductAPIService;
import com.jocata.service.UserAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private OrderAPIService orderAPIService;

    @Autowired
    private UserAPIService userAPIService;

    @Autowired
    private ProductAPIService productAPIService;

    @Override
    public List<InvoiceForm> getInvoicesByUserId(String userId) {
        List<Invoice> invoices = invoiceDao.findByUserId(Long.valueOf(userId));
        List<InvoiceForm> forms = new ArrayList<>();

        for (Invoice invoice : invoices) {
            InvoiceForm form = new InvoiceForm();
            form.setId(String.valueOf(invoice.getId()));
            form.setTransactionId(String.valueOf(invoice.getTransactionId()));
            form.setUserId(String.valueOf(invoice.getUserId()));
            form.setAmount(invoice.getAmount().toString());
            form.setInvoiceDate(invoice.getInvoiceDate().toString());
            form.setOrderId(String.valueOf(invoice.getOrderId()));
            forms.add(form);
        }
        return forms;
    }

    @Override
    public InvoiceForm getInvoiceByOrderId(String orderId) {
        Invoice invoice = invoiceDao.findByOrderId(Long.valueOf(orderId));
        if (invoice != null) {
            InvoiceForm form = new InvoiceForm();
            form.setId(String.valueOf(invoice.getId()));
            form.setTransactionId(String.valueOf(invoice.getTransactionId()));
            form.setUserId(String.valueOf(invoice.getUserId()));
            form.setAmount(invoice.getAmount().toString());
            form.setInvoiceDate(invoice.getInvoiceDate().toString());
            form.setOrderId(String.valueOf(invoice.getOrderId()));
            return form;
        }
        return null;
    }

    @Override
    public byte[] generateInvoicePdf(String orderId) {

        OrderResponseForm order = orderAPIService.getOrderByOrderId(orderId);
        Invoice invoice = invoiceDao.findByOrderId(Long.parseLong(orderId));

        UserForm userForm = userAPIService.getUser(order.getUserId());
        if (order == null || invoice == null) {
            return null;
        }

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("INVOICE", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));


            document.add(new Paragraph("Invoice ID: " + invoice.getId()));
            document.add(new Paragraph("Order ID: " + orderId));
            document.add(new Paragraph("User ID: " + invoice.getUserId()));
            document.add(new Paragraph("User name: "+userForm.getUsername()));
            document.add(new Paragraph("Transaction ID: " + invoice.getTransactionId()));
            document.add(new Paragraph("Invoice Date: " + invoice.getInvoiceDate()));
            document.add(new Paragraph("Total Amount Paid: ₹" + invoice.getAmount()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1,3, 1, 2});
            addTableHeader(table, "Product ID","Product Name", "Quantity", "Price");

            for (OrderItemForm item : order.getItems()) {
                table.addCell(item.getProductId());
                ProductForm form = productAPIService.getProductById(item.getProductId());
                table.addCell(form.getName());
                table.addCell(item.getQuantity());
                table.addCell("₹" + item.getPrice());
            }

            document.add(table);
            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }

}
