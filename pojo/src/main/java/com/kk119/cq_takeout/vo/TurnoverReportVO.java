package com.kk119.cq_takeout.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class TurnoverReportVO implements Serializable {
    /**
     * 日期，以逗号分隔
     */
    private String dateList;

    /**
     * 营业额，以逗号分割
     */
    private String turnoverList;

    public static void main(String[] args) {
        LocalDate begin = LocalDate.of(2023, 7, 1);
        LocalDate end = LocalDate.of(2023, 7, 10);

        StringBuilder dateList = new StringBuilder();

        while (begin.isBefore(end)) {
            String date = begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            log.info("当前日期：{}", date);

            if (begin.until(end, ChronoUnit.DAYS) == 0) {
                dateList.append(date);
            } else {
                dateList.append(date).append(",");
            }
            begin = begin.plusDays(1);
        }
        log.info("日期列表：{}", dateList);
    }
}
