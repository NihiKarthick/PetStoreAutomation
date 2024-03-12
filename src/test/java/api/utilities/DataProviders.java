package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata//DataFile.xlsx";
		ExcelUtil xl=new ExcelUtil(path);
		
		int rownum=xl.getRowCount("sheet");
		int colcount=xl.getCellCount("sheet",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getCellData("sheet", i, j);
			}
		}
		return apidata;
	}
	

	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata//DataFile.xlsx";
		ExcelUtil xl=new ExcelUtil(path);
		
		int rownum=xl.getRowCount("sheet");
		
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getCellData("sheet", i, 1);
		}

		return apidata;

	}
}