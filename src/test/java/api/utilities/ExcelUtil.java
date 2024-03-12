package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet1;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtil(String path)
	{
		
		this.path=path;
	}
	
	public int getRowCount(String sheet) throws IOException
	{
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet1=workbook.getSheet(sheet);
		int rowcount=sheet1.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheet,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet1=workbook.getSheet(sheet);
		row=sheet1.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
	    return cellcount;
	}
	
	public String getCellData(String sheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet1=workbook.getSheet(sheet);
		row=sheet1.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
			String data;
			try {
				data=formatter.formatCellValue(cell);
			}
			catch(Exception e)
			{
				data="";
			}
			workbook.close();
			fi.close();
			return data;
	}
	public void setCellData(String sheet,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheet)==-1)
			workbook.createSheet(sheet);
		sheet1=workbook.getSheet(sheet);
		
		if(sheet1.getRow(rownum)==null)
			sheet1.createRow(rownum);
		row=sheet1.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	

}