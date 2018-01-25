package com.lily.study.entity;

import com.lily.study.util.ArrayUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Forecast implements  Comparable<Forecast>,java.io.Serializable {
	// sku,unit1,unit2,model_id,forecast_days,mean_list,std_list,mean_of_next_7_days,recent_28_days_avg,recent_28_days_std,uplift,area,sale_summary,sale_status,eligible_days,outlet_days,seasonal_peak_days,oos_days,outlier_days

	private int sku;

	private String unit1;

	private String unit2;

	private String modelId;

	private int forecastDays;

	private double[] meanList;

	private double[] stdList;

	private double meanOfNext7Days;

	private double recent28DaysAvg;

	private double recent28DaysStd;

	private String uplift;

	private String area;

	private String saleSummary;

	private String saleStatus;

	private String eligibleDays;

	private String outletDays;

	private String seasonalPeakDays;

	private String oosDays;

	private String outlierDays;

	public static Forecast parseForecast(String str) {
		if (StringUtils.isBlank(str))
			return null;

		List<String> arrays = Forecast.splitStr(str);

		Forecast forecast = new Forecast();

		forecast.setSku(ArrayUtil.toInteger(arrays.get(0)));
		forecast.setUnit1(arrays.get(1));
		forecast.setUnit2(arrays.get(2));

		forecast.setModelId(arrays.get(3));
		forecast.setForecastDays(ArrayUtil.toInteger(arrays.get(4)));

		String mean=arrays.get(5);

		//mean=StringUtils.substring(mean,1,mean.length()-1);

		forecast.setMeanList(ArrayUtil.toDouble(mean.split(",")));


		String std=arrays.get(6);
		//std=StringUtils.substring(std,1,mean.length()-1);

		forecast.setStdList(ArrayUtil.toDouble(std.split(",")));


		forecast.setMeanOfNext7Days(ArrayUtil.toSingleDouble(arrays.get(7)));

		forecast.setRecent28DaysAvg(ArrayUtil.toSingleDouble(arrays.get(8)));

		forecast.setRecent28DaysStd(ArrayUtil.toSingleDouble(arrays.get(9)));

		forecast.setUplift(arrays.get(10));

		forecast.setArea(arrays.get(11));
		forecast.setSaleSummary(arrays.get(12));

		forecast.setSaleStatus(arrays.get(13));

		forecast.setEligibleDays(arrays.get(14));

		forecast.setOutletDays(arrays.get(15));

		forecast.setSeasonalPeakDays(arrays.get(16));

		forecast.setOosDays(arrays.get(17));

		forecast.setOutlierDays(arrays.get(18));

		return forecast;

	}


	public static  List<String> splitStr(String str){

		List<String> testList=new ArrayList<>();


		while(str !=null && str.length()>0){
             int start=0;
			if(str.startsWith("\"")){
				start=1;
			}
			int end=0;
			 if(start==1){
				 end=str.indexOf("\"",start);
			 }

			if(end <=0){
				end=str.indexOf(",");
			}

			if(end <=0){
			 	end=str.length();
			}

			if(end <=0){
			 	end=str.length();
			}
			String pre=str.substring(start,end);
			testList.add(pre);

			 if((end+1)>str.length() ){
				 str=null;
			 }
			else {
				 str=(start==1)?str.substring(end+2):str.substring(end+1);
			 }


		}

		return  testList;
	}

	@Override
	public String toString() {
		return "Forecast{" +
			"sku=" + sku +
			", unit1='" + unit1 + '\'' +
			", unit2='" + unit2 + '\'' +
			", modelId='" + modelId + '\'' +
			", forecastDays=" + forecastDays +
			", meanList=" + Arrays.toString(meanList) +
			", stdList=" + Arrays.toString(stdList) +
			", meanOfNext7Days=" + meanOfNext7Days +
			", recent28DaysAvg=" + recent28DaysAvg +
			", recent28DaysStd=" + recent28DaysStd +
			", uplift='" + uplift + '\'' +
			", area='" + area + '\'' +
			", saleSummary='" + saleSummary + '\'' +
			", saleStatus='" + saleStatus + '\'' +
			", eligibleDays='" + eligibleDays + '\'' +
			", outletDays='" + outletDays + '\'' +
			", seasonalPeakDays='" + seasonalPeakDays + '\'' +
			", oosDays='" + oosDays + '\'' +
			", outlierDays='" + outlierDays + '\'' +
			'}';
	}

	public static void main(String[] args){
      String test="100704,Books,Children Books,10,35,\"0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0\",\"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\",0.0,0,0,1,national,No sales,HALT_PRODUCTION,0,0,0,0,0";


     // List<String> stringList=Forecast.splitStr(test);

		//System.out.println(stringList);

		Forecast f=Forecast.parseForecast(test);

		System.out.println(f);

	}

	@Override
	public int compareTo(Forecast o) {

		if(o==null) return -1;

		if (this.meanOfNext7Days==o.meanOfNext7Days) return 0;

		if(this.meanOfNext7Days>o.meanOfNext7Days) return -1;

		return 1;



	}
}
