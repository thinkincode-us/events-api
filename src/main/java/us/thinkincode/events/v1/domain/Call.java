package us.thinkincode.events.v1.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Call {

    public Call(Integer accountId, Integer accountServiceId, LocalDateTime startTime, LocalDateTime endTime, String callId, BigDecimal amount) {
        this.accountId = accountId;
        this.accountServiceId = accountServiceId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.callId = callId;
        this.amount = amount;
    }

    private Integer accountId;
    private Integer accountServiceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String callId;
    private BigDecimal amount;

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getAccountServiceId() {
        return accountServiceId;
    }

    public int getDurationInMinutes() {
        return Double.valueOf(
                Math.ceil(SECONDS.between(startTime, endTime) / 60f)
        ).intValue();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
