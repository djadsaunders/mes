package com.djad.mes.domain.resource;

import com.djad.mes.domain.crew.Crew;
import com.djad.mes.domain.crew.CrewPeriod;
import com.djad.mes.domain.product.ProductionRun;
import com.djad.mes.domain.product.ProductionRunPeriod;
import com.djad.mes.domain.shift.Shift;
import com.djad.mes.domain.shift.ShiftPeriod;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="resource")
public class Resource {

    public static final String NO_DATA = "none";

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="tag")
    private String tag;

    @Column(name="name")
    private String name;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private CrewPeriod currentCrewPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private CrewPeriod previousCrewPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private ShiftPeriod currentShiftPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private ShiftPeriod previousShiftPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private ProductionRunPeriod currentProductionRunPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private ProductionRunPeriod previousProductionRunPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private ProductionStatePeriod currentProductionStatePeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private ProductionStatePeriod previousProductionStatePeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private AvailabilityPeriod currentAvailabilityPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private AvailabilityPeriod previousAvailabilityPeriod;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Counter currentCounterIn;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Counter previousCounterIn;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Counter currentCounterOut;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Counter previousCounterOut;

    public Resource() {
    }

    public Resource(String tag) {
        this.setTag(tag);
        this.currentProductionStatePeriod = new ProductionStatePeriod(ProductionState.STOPPED, this);
        this.currentAvailabilityPeriod = new AvailabilityPeriod(Availability.UNAVAILABLE, this);
    }

    public boolean makeAvailable() {
        if (this.isAvailable()) return false;
        return this.changeAvailability(Availability.AVAILABLE);
    }

    public boolean makeUnavailable() {
        if (!this.isAvailable()) return false;
        return this.changeAvailability(Availability.UNAVAILABLE);
    }

    public boolean isAvailable() {
        return this.currentAvailabilityPeriod.isAvailable();
    }

    private boolean changeAvailability(Availability availabilityState) {
        this.previousAvailabilityPeriod = this.currentAvailabilityPeriod;
        this.previousAvailabilityPeriod.setTo(new Date());
        this.currentAvailabilityPeriod = new AvailabilityPeriod(availabilityState, this);
        return true;
    }

    public boolean setRunningFullSpeed() {
        if (this.isRunningFullSpeed()) return false;
        return this.changeProductionState(ProductionState.RUNNING);
    }

    public boolean setRunningSlow() {
        if (this.isRunningSlow()) return false;
        return this.changeProductionState(ProductionState.SLOW);
    }

    public boolean stop() {
        if (this.isStopped()) return false;
        return this.changeProductionState(ProductionState.STOPPED);
    }

    private boolean changeProductionState(ProductionState productionState) {
        this.previousProductionStatePeriod = this.currentProductionStatePeriod;
        this.previousProductionStatePeriod.setTo(new Date());
        this.currentProductionStatePeriod = new ProductionStatePeriod(productionState, this);
        return true;
    }

    public boolean isRunning() {
        return this.currentProductionStatePeriod.isRunning();
    }

    public boolean isRunningFullSpeed() {
        return this.currentProductionStatePeriod.isRunningFullSpeed();
    }

    public boolean isRunningSlow() {
        return this.currentProductionStatePeriod.isRunningSlow();
    }

    public boolean isStopped() {
        return this.currentProductionStatePeriod.isStopped();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean changeCrew(Crew crew) {
        Date changeDate = new Date();

        if (this.getCurrentCrew().isPresent() && this.getCurrentCrew().get().getName().equals(crew.getName())) return false;

        this.previousCrewPeriod = this.currentCrewPeriod;

        if (this.previousCrewPeriod != null) {
            this.previousCrewPeriod.setTo(changeDate);
        }

        this.currentCrewPeriod = new CrewPeriod(crew, this);

        return true;
    }

    public Optional<Crew> getCurrentCrew() {
        return (this.getCurrentCrewPeriod().isPresent() ? Optional.of(this.currentCrewPeriod.getCrew()) : Optional.empty());
    }

    public Optional<CrewPeriod> getCurrentCrewPeriod() {
        return Optional.ofNullable(this.currentCrewPeriod);
    }

    public Optional<Crew> getPreviousCrew() {
        return (this.getPreviousCrewPeriod().isPresent() ? Optional.of(this.previousCrewPeriod.getCrew()) : Optional.empty());
    }

    public Optional<CrewPeriod> getPreviousCrewPeriod() {
        return Optional.ofNullable(this.previousCrewPeriod);
    }

    public boolean changeShift(Shift shift) {
        Date changeDate = new Date();

        if (this.getCurrentShift().isPresent() && this.getCurrentShift().get().getName().equals(shift.getName())) return false;

        this.previousShiftPeriod = this.currentShiftPeriod;

        if (this.previousShiftPeriod != null) {
            this.previousShiftPeriod.setTo(changeDate);
        }

        this.currentShiftPeriod = new ShiftPeriod(shift, this);

        return true;
    }

    public Optional<Shift> getCurrentShift() {
        return (this.getCurrentShiftPeriod().isPresent() ? Optional.of(this.currentShiftPeriod.getShift()) : Optional.empty());
    }

    public Optional<ShiftPeriod> getCurrentShiftPeriod() {
        return Optional.ofNullable(this.currentShiftPeriod);
    }

    public Optional<Shift> getPreviousShift() {
        return (this.getPreviousShiftPeriod().isPresent() ? Optional.of(this.previousShiftPeriod.getShift()) : Optional.empty());
    }

    public Optional<ShiftPeriod> getPreviousShiftPeriod() {
        return Optional.ofNullable(this.previousShiftPeriod);
    }

    public boolean changeProductionRun(ProductionRun productionRun) {
        Date changeDate = new Date();

        if (this.getCurrentProductionRun().isPresent() && this.getCurrentProductionRun().get().getName().equals(productionRun.getName()))
            return false;

        this.previousProductionRunPeriod = this.currentProductionRunPeriod;

        if (this.previousProductionRunPeriod != null) {
            this.previousProductionRunPeriod.setTo(changeDate);
        }

        this.currentProductionRunPeriod = new ProductionRunPeriod(productionRun, this);

        return true;
    }

    public Optional<ProductionRun> getCurrentProductionRun() {
        return (this.getCurrentProductionRunPeriod().isPresent() ? Optional.of(this.currentProductionRunPeriod.getProductionRun()) : Optional.empty());
    }

    public Optional<ProductionRunPeriod> getCurrentProductionRunPeriod() {
        return Optional.ofNullable(this.currentProductionRunPeriod);
    }

    public Optional<ProductionRun> getPreviousProductionRun() {
        return (this.getPreviousProductionRunPeriod().isPresent() ? Optional.of(this.previousProductionRunPeriod.getProductionRun()) : Optional.empty());
    }

    public Optional<ProductionRunPeriod> getPreviousProductionRunPeriod() {
        return Optional.ofNullable(this.previousProductionRunPeriod);
    }

    public Counter getCurrentInCount() {
        return this.currentCounterIn;
    }

    public double getCurrentInCountValue() {
        return this.currentCounterIn == null ? 0.0 : this.currentCounterIn.getValue();
    }

    public Counter getPreviousInCount() {
        return this.previousCounterIn;
    }

    public double getPreviousInCountValue() {
        return this.previousCounterIn == null ? 0.0 : this.previousCounterIn.getValue();
    }

    public Counter getCurrentOutCount() {
        return this.currentCounterOut;
    }

    public double getCurrentOutCountValue() {
        return this.currentCounterOut == null ? 0.0 : this.currentCounterOut.getValue();
    }

    public Counter getPreviousOutCount() {
        return this.previousCounterOut;
    }

    public double getPreviousOutCountValue() {
        return this.previousCounterOut == null ? 0.0 : this.previousCounterOut.getValue();
    }

    public void logInCount(double value) {
        this.logCount(Counter.CounterTypes.IN, value);
    }

    public void logOutCount(double value) {
        this.logCount(Counter.CounterTypes.OUT, value);
    }

    private void logCount(Counter.CounterTypes type, double value) {
        if (type == Counter.CounterTypes.IN) {
            this.previousCounterIn = this.currentCounterIn;
            this.currentCounterIn = new Counter(Counter.CounterTypes.IN, this, value);
        }
        else {
            this.previousCounterOut = this.currentCounterOut;
            this.currentCounterOut = new Counter(Counter.CounterTypes.OUT, this, value);
        }
    }

    @Override
    public String toString() {
        return this.getTag() + " (" + this.getName() + ")";
    }
}
