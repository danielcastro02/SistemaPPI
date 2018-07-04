package Controle;

import Modelo.Cliente;
import Modelo.Funcionario;
import Modelo.Gasto;
import Modelo.Onibus;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


//Gera os diversos relatorios do sistema
public class PDF {

    
    //Gera o relatorio dos clientes 
    public void realtorioCli() throws ClassNotFoundException, FileNotFoundException, DocumentException {
        ContCli cocli = new ContCli();
        

        //Instancia um novo documento
        Document doc = new Document();
        
        //Define o local do documento
        PdfWriter objpdf = PdfWriter.getInstance(doc, new FileOutputStream("relatorioClientes.pdf"));
        doc.open();
        PdfPTable tb = new PdfPTable(1);
        tb.setWidthPercentage(50);
        tb.setHorizontalAlignment(Element.ALIGN_CENTER);
        tb.setSpacingAfter(10f);
        tb.setSpacingBefore(10f);
        
        PdfPCell cel = new PdfPCell(new Paragraph("Relatório Clientes."));
        cel.setBorderColor(BaseColor.BLACK);
        cel.setPaddingLeft(10);
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel.setVerticalAlignment(Element.ALIGN_MIDDLE);

        tb.addCell(cel);

        doc.add(tb);

        try {
            PdfPTable tabela = new PdfPTable(4); // 4 colunas.
            tabela.setWidthPercentage(100); //largura 100%
            tabela.setSpacingBefore(10f); //espaços antes da tabela
            tabela.setSpacingAfter(10f); //espaços antes da tabela

            float[] larguraColuna = {1f, 1f, 1f, 1f};
            tabela.setWidths(larguraColuna);

            PdfPCell celula01 = new PdfPCell(new Paragraph("Código"));
            celula01.setBorderColor(BaseColor.BLACK);
            celula01.setPaddingLeft(10);
            celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula02 = new PdfPCell(new Paragraph("Nome"));
            celula02.setBorderColor(BaseColor.BLACK);
            celula02.setPaddingLeft(10);
            celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula03 = new PdfPCell(new Paragraph("CPF"));
            celula03.setBorderColor(BaseColor.BLACK);
            celula03.setPaddingLeft(10);
            celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula04 = new PdfPCell(new Paragraph("Divida"));
            celula04.setBorderColor(BaseColor.BLACK);
            celula04.setPaddingLeft(10);
            celula04.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula04.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabela.addCell(celula01);
            tabela.addCell(celula02);
            tabela.addCell(celula03);
            tabela.addCell(celula04);
            for (Cliente cli : cocli.selecCli()) {

                //Set Column widths
                //Para evitar que a borda da célula e o conteúdo se sobreponham, 
                //se você tiver tendo bordas de células grossas
                //celula01.setUserBorderPadding(true);
                //celula02.setUserBorderPadding(true);
                //celula03.setUserBorderPadding(true);
                doc.add(new Paragraph());

                celula01 = new PdfPCell(new Paragraph("" + cli.getCod()));
                celula01.setBorderColor(BaseColor.BLACK);
                celula01.setPaddingLeft(10);
                celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula02 = new PdfPCell(new Paragraph(cli.getNome()));
                celula02.setBorderColor(BaseColor.BLACK);
                celula02.setPaddingLeft(10);
                celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula03 = new PdfPCell(new Paragraph(cli.getCpf()));
                celula03.setBorderColor(BaseColor.BLACK);
                celula03.setPaddingLeft(10);
                celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula04 = new PdfPCell(new Paragraph("R$" + cli.getDiv()));
                celula04.setBorderColor(BaseColor.BLACK);
                celula04.setPaddingLeft(10);
                celula04.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula04.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabela.addCell(celula01);
                tabela.addCell(celula02);
                tabela.addCell(celula03);
                tabela.addCell(celula04);

//                String linha = "";
//                linha += ("Codigo: " + contatos.getCodigoContato());
//                linha += (" Nome: " + contatos.getNomeContato());
//                linha += (" Celular: " + contatos.getNumCelular());
//                linha += (" Fixo: " + contatos.getNumFixo());
//                documento.add(new Paragraph(linha));
            }
            doc.add(tabela);

            doc.close();
            objpdf.close();
            doc.close();
            objpdf.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PDF pd = new PDF();
        pd.abreArquivoPdf("relatorioClientes.pdf");
    }
    
    
    //Recebe o local do arquivo e abre no visualizador de pdf padrão
    public void abreArquivoPdf(String arquivopdf) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(arquivopdf);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }
    
    
    //Gera o relatório dos onibus
    public void realtorioOni() throws ClassNotFoundException, FileNotFoundException, DocumentException {
        ContOni cocli = new ContOni();

        Document doc = new Document();
        PdfWriter objpdf = PdfWriter.getInstance(doc, new FileOutputStream("relatorioOnibus.pdf"));
        doc.open();
        PdfPTable tb = new PdfPTable(1);
        tb.setWidthPercentage(50);
        tb.setHorizontalAlignment(Element.ALIGN_CENTER);
        tb.setSpacingAfter(10f);
        tb.setSpacingBefore(10f);

        PdfPCell cel = new PdfPCell(new Paragraph("Relatório Onibus."));
        cel.setBorderColor(BaseColor.BLACK);
        cel.setPaddingLeft(10);
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel.setVerticalAlignment(Element.ALIGN_MIDDLE);

        tb.addCell(cel);

        doc.add(tb);

        try {
            PdfPTable tabela = new PdfPTable(3); // 4 colunas.
            tabela.setWidthPercentage(100); //largura 100%
            tabela.setSpacingBefore(10f); //espaços antes da tabela
            tabela.setSpacingAfter(10f); //espaços antes da tabela

            float[] larguraColuna = {1f, 1f, 1f};
            tabela.setWidths(larguraColuna);

            PdfPCell celula01 = new PdfPCell(new Paragraph("Código"));
            celula01.setBorderColor(BaseColor.BLACK);
            celula01.setPaddingLeft(10);
            celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula02 = new PdfPCell(new Paragraph("Modelo"));
            celula02.setBorderColor(BaseColor.BLACK);
            celula02.setPaddingLeft(10);
            celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula03 = new PdfPCell(new Paragraph("Salário"));
            celula03.setBorderColor(BaseColor.BLACK);
            celula03.setPaddingLeft(10);
            celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabela.addCell(celula01);
            tabela.addCell(celula02);
            tabela.addCell(celula03);
            for (Onibus cli : cocli.selectOni()) {

                //Set Column widths
                //Para evitar que a borda da célula e o conteúdo se sobreponham, 
                //se você tiver tendo bordas de células grossas
                //celula01.setUserBorderPadding(true);
                //celula02.setUserBorderPadding(true);
                //celula03.setUserBorderPadding(true);
                doc.add(new Paragraph());

                celula01 = new PdfPCell(new Paragraph("" + cli.getCod()));
                celula01.setBorderColor(BaseColor.BLACK);
                celula01.setPaddingLeft(10);
                celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula02 = new PdfPCell(new Paragraph(cli.getModelo()));
                celula02.setBorderColor(BaseColor.BLACK);
                celula02.setPaddingLeft(10);
                celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula03 = new PdfPCell(new Paragraph("R$" + cli.getGasto()));
                celula03.setBorderColor(BaseColor.BLACK);
                celula03.setPaddingLeft(10);
                celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabela.addCell(celula01);
                tabela.addCell(celula02);
                tabela.addCell(celula03);

//                String linha = "";
//                linha += ("Codigo: " + contatos.getCodigoContato());
//                linha += (" Nome: " + contatos.getNomeContato());
//                linha += (" Celular: " + contatos.getNumCelular());
//                linha += (" Fixo: " + contatos.getNumFixo());
//                documento.add(new Paragraph(linha));
            }
            doc.add(tabela);

            doc.close();
            objpdf.close();
            doc.close();
            objpdf.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PDF pd = new PDF();
        pd.abreArquivoPdf("relatorioOnibus.pdf");
    }
    
    
    //gera o relatório dos gastos
    public void realtorioGas() throws ClassNotFoundException, FileNotFoundException, DocumentException {
        ContGast gas = new ContGast();

        Document doc = new Document();
        PdfWriter objpdf = PdfWriter.getInstance(doc, new FileOutputStream("relatorioGastos.pdf"));
        doc.open();
        PdfPTable tb = new PdfPTable(1);
        tb.setWidthPercentage(50);
        tb.setHorizontalAlignment(Element.ALIGN_CENTER);
        tb.setSpacingAfter(10f);
        tb.setSpacingBefore(10f);

        PdfPCell cel = new PdfPCell(new Paragraph("Relatório de Gastos."));
        cel.setBorderColor(BaseColor.BLACK);
        cel.setPaddingLeft(10);
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel.setVerticalAlignment(Element.ALIGN_MIDDLE);

        tb.addCell(cel);

        doc.add(tb);

        try {
            PdfPTable tabela = new PdfPTable(4); // 4 colunas.
            tabela.setWidthPercentage(100); //largura 100%
            tabela.setSpacingBefore(10f); //espaços antes da tabela
            tabela.setSpacingAfter(10f); //espaços antes da tabela

            float[] larguraColuna = {1f, 1f, 2f, 1f};
            tabela.setWidths(larguraColuna);

            PdfPCell celula01 = new PdfPCell(new Paragraph("Código"));
            celula01.setBorderColor(BaseColor.BLACK);
            celula01.setPaddingLeft(10);
            celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula02 = new PdfPCell(new Paragraph("Codigo do Ônibus"));
            celula02.setBorderColor(BaseColor.BLACK);
            celula02.setPaddingLeft(10);
            celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula03 = new PdfPCell(new Paragraph("Descrção"));
            celula03.setBorderColor(BaseColor.BLACK);
            celula03.setPaddingLeft(10);
            celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula04 = new PdfPCell(new Paragraph("Valor"));
            celula04.setBorderColor(BaseColor.BLACK);
            celula04.setPaddingLeft(10);
            celula04.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula04.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabela.addCell(celula01);
            tabela.addCell(celula02);
            tabela.addCell(celula03);
            tabela.addCell(celula04);
            for (Gasto gast : gas.selecGas()) {

                //Set Column widths
                //Para evitar que a borda da célula e o conteúdo se sobreponham, 
                //se você tiver tendo bordas de células grossas
                //celula01.setUserBorderPadding(true);
                //celula02.setUserBorderPadding(true);
                //celula03.setUserBorderPadding(true);
                doc.add(new Paragraph());

                celula01 = new PdfPCell(new Paragraph("" + gast.getCod()));
                celula01.setBorderColor(BaseColor.BLACK);
                celula01.setPaddingLeft(10);
                celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula02 = new PdfPCell(new Paragraph(""+gast.getCodbus()));
                celula02.setBorderColor(BaseColor.BLACK);
                celula02.setPaddingLeft(10);
                celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula03 = new PdfPCell(new Paragraph(gast.getDesc()));
                celula03.setBorderColor(BaseColor.BLACK);
                celula03.setPaddingLeft(10);
                celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula04 = new PdfPCell(new Paragraph("R$" + gast.getVal()));
                celula04.setBorderColor(BaseColor.BLACK);
                celula04.setPaddingLeft(10);
                celula04.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula04.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabela.addCell(celula01);
                tabela.addCell(celula02);
                tabela.addCell(celula03);
                tabela.addCell(celula04);

//                String linha = "";
//                linha += ("Codigo: " + contatos.getCodigoContato());
//                linha += (" Nome: " + contatos.getNomeContato());
//                linha += (" Celular: " + contatos.getNumCelular());
//                linha += (" Fixo: " + contatos.getNumFixo());
//                documento.add(new Paragraph(linha));
            }
            doc.add(tabela);

            doc.close();
            objpdf.close();
            doc.close();
            objpdf.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PDF pd = new PDF();
        pd.abreArquivoPdf("relatorioGastos.pdf");
    }

    //Gera o relatório dos funcionarios
    public void realtorioFun() throws ClassNotFoundException, FileNotFoundException, DocumentException {
        ContFuncion cofun = new ContFuncion();

        Document doc = new Document();
        PdfWriter objpdf = PdfWriter.getInstance(doc, new FileOutputStream("relatorioFuncionarios.pdf"));
        doc.open();
        PdfPTable tb = new PdfPTable(1);
        tb.setWidthPercentage(50);
        tb.setHorizontalAlignment(Element.ALIGN_CENTER);
        tb.setSpacingAfter(10f);
        tb.setSpacingBefore(10f);

        PdfPCell cel = new PdfPCell(new Paragraph("Relatório Funcionários."));
        cel.setBorderColor(BaseColor.BLACK);
        cel.setPaddingLeft(10);
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel.setVerticalAlignment(Element.ALIGN_MIDDLE);

        tb.addCell(cel);

        doc.add(tb);

        try {
            PdfPTable tabela = new PdfPTable(4); // 4 colunas.
            tabela.setWidthPercentage(100); //largura 100%
            tabela.setSpacingBefore(10f); //espaços antes da tabela
            tabela.setSpacingAfter(10f); //espaços antes da tabela

            float[] larguraColuna = {1f, 1f, 1f, 1f};
            tabela.setWidths(larguraColuna);

            PdfPCell celula01 = new PdfPCell(new Paragraph("Código"));
            celula01.setBorderColor(BaseColor.BLACK);
            celula01.setPaddingLeft(10);
            celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula02 = new PdfPCell(new Paragraph("Nome"));
            celula02.setBorderColor(BaseColor.BLACK);
            celula02.setPaddingLeft(10);
            celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula03 = new PdfPCell(new Paragraph("CPF"));
            celula03.setBorderColor(BaseColor.BLACK);
            celula03.setPaddingLeft(10);
            celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell celula04 = new PdfPCell(new Paragraph("Salário"));
            celula04.setBorderColor(BaseColor.BLACK);
            celula04.setPaddingLeft(10);
            celula04.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula04.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabela.addCell(celula01);
            tabela.addCell(celula02);
            tabela.addCell(celula03);
            tabela.addCell(celula04);
            for (Funcionario fun : cofun.selectFun()) {

                //Set Column widths
                //Para evitar que a borda da célula e o conteúdo se sobreponham, 
                //se você tiver tendo bordas de células grossas
                //celula01.setUserBorderPadding(true);
                //celula02.setUserBorderPadding(true);
                //celula03.setUserBorderPadding(true);
                doc.add(new Paragraph());

                celula01 = new PdfPCell(new Paragraph("" + fun.getCod()));
                celula01.setBorderColor(BaseColor.BLACK);
                celula01.setPaddingLeft(10);
                celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula01.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula02 = new PdfPCell(new Paragraph(fun.getNome()));
                celula02.setBorderColor(BaseColor.BLACK);
                celula02.setPaddingLeft(10);
                celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula02.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula03 = new PdfPCell(new Paragraph(fun.getCpf()));
                celula03.setBorderColor(BaseColor.BLACK);
                celula03.setPaddingLeft(10);
                celula03.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula03.setVerticalAlignment(Element.ALIGN_MIDDLE);

                celula04 = new PdfPCell(new Paragraph("R$" + fun.getSal()));
                celula04.setBorderColor(BaseColor.BLACK);
                celula04.setPaddingLeft(10);
                celula04.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula04.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabela.addCell(celula01);
                tabela.addCell(celula02);
                tabela.addCell(celula03);
                tabela.addCell(celula04);

//                String linha = "";
//                linha += ("Codigo: " + contatos.getCodigoContato());
//                linha += (" Nome: " + contatos.getNomeContato());
//                linha += (" Celular: " + contatos.getNumCelular());
//                linha += (" Fixo: " + contatos.getNumFixo());
//                documento.add(new Paragraph(linha));
            }
            doc.add(tabela);

            doc.close();
            objpdf.close();
            doc.close();
            objpdf.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PDF pd = new PDF();
        pd.abreArquivoPdf("relatorioFuncionarios.pdf");
    }
    
}
