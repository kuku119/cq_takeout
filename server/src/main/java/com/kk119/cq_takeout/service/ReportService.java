package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.vo.OrderReportVO;
import com.kk119.cq_takeout.vo.SalesTop10ReportVO;
import com.kk119.cq_takeout.vo.TurnoverReportVO;
import com.kk119.cq_takeout.vo.UserReportVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Service
public interface ReportService {
    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);

    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    SalesTop10ReportVO top10(LocalDate begin, LocalDate end);

    void exportBusinessData(HttpServletResponse response);
}
