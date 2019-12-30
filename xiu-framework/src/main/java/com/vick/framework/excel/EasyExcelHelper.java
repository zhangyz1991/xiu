package com.vick.framework.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.analysis.ExcelAnalyser;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.ReadWorkbook;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author zyz
 * @since 2019-12-27
 */
@Component
public class EasyExcelHelper {

    public void read(InputStream is, Object customObject, AnalysisEventListener listener) {
        ReadWorkbook workbook = new ReadWorkbook();
        workbook.setExcelType(ExcelTypeEnum.XLS);
        workbook.setInputStream(is);
        workbook.setCustomObject(customObject);
        ExcelReader excelReader = new ExcelReader(workbook);
        excelReader.read(new ReadSheet());
    }

}
