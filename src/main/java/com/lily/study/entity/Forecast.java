package com.lily.study.entity;

import com.lily.study.util.ArrayUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Forecast implements java.io.Serializable {
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

		String[] arrays = str.split(",");

		Forecast forecast = new Forecast();

		forecast.setSku(Integer.valueOf(arrays[0]));
		forecast.setUnit1(arrays[1]);
		forecast.setUnit2(arrays[2]);

		forecast.setModelId(arrays[3]);
		forecast.setForecastDays(Integer.valueOf(arrays[4]));

		forecast.setMeanList(ArrayUtil.toDouble(arrays[5].split(",")));

		forecast.setStdList(ArrayUtil.toDouble(arrays[6].split(",")));

		forecast.setMeanOfNext7Days(Double.valueOf(arrays[7]));

		forecast.setRecent28DaysAvg(Double.valueOf(arrays[8]));

		forecast.setRecent28DaysStd(Double.valueOf(arrays[9]));

		forecast.setUplift(arrays[10]);

		forecast.setArea(arrays[11]);
		forecast.setSaleSummary(arrays[12]);

		forecast.setSaleStatus(arrays[13]);

		forecast.setEligibleDays(arrays[14]);

		forecast.setOutletDays(arrays[15]);

		forecast.setSeasonalPeakDays(arrays[16]);

		forecast.setOosDays(arrays[17]);

		forecast.setOutlierDays(arrays[18]);

		return forecast;

	}



}
