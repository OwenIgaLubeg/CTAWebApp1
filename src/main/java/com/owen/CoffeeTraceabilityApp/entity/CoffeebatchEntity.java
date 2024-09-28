package com.owen.CoffeeTraceabilityApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coffeeBatch")
public class CoffeebatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "batch_name", nullable = false, unique = true)
    private String batchName;

    @ManyToOne
    @JoinColumn(name = "farmer_id", referencedColumnName = "farmer_id")
    private FarmerEntity farmer;

    @ManyToOne
    @JoinColumn(name = "intermediary_id", referencedColumnName = "intermediary_id")
    private IntermediaryEntity intermediary;

    @ManyToOne
    @JoinColumn(name = "association_id", referencedColumnName = "association_id")
    private AssociationEntity association;

    @ManyToOne
    @JoinColumn(name = "processor_id", referencedColumnName = "processor_id")
    private ProcessorEntity processor;

    @ManyToOne
    @JoinColumn(name = "exporter_id", referencedColumnName = "exporter_id")
    private ExporterEntity exporter;

    @ManyToOne
    @JoinColumn(name = "roaster_id", referencedColumnName = "roaster_id")
    private RoastingFirms roaster;

    @ManyToOne
    @JoinColumn(name = "cafeteria_id", referencedColumnName = "cafeteria_id")
    private Cafeterias cafeteria;

    @Column(name = "current_status", nullable = false)
    private String currentStatus;

    @Column(name = "tracking_history", columnDefinition = "TEXT")
    private String trackingHistory;

    // Getters and Setters
	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public FarmerEntity getFarmer() {
		return farmer;
	}

	public void setFarmer(FarmerEntity farmer) {
		this.farmer = farmer;
	}

	public IntermediaryEntity getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(IntermediaryEntity intermediary) {
		this.intermediary = intermediary;
	}

	public AssociationEntity getAssociation() {
		return association;
	}

	public void setAssociation(AssociationEntity association) {
		this.association = association;
	}

	public ProcessorEntity getProcessor() {
		return processor;
	}

	public void setProcessor(ProcessorEntity processor) {
		this.processor = processor;
	}

	public ExporterEntity getExporter() {
		return exporter;
	}

	public void setExporter(ExporterEntity exporter) {
		this.exporter = exporter;
	}

	public RoastingFirms getRoaster() {
		return roaster;
	}

	public void setRoaster(RoastingFirms roaster) {
		this.roaster = roaster;
	}

	public Cafeterias getCafeteria() {
		return cafeteria;
	}

	public void setCafeteria(Cafeterias cafeteria) {
		this.cafeteria = cafeteria;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentSttus) {
		this.currentStatus = currentStatus;
	}

	public String getTrackingHistory() {
		return trackingHistory;
	}

	public void setTrackingHistory(String trackingHistory) {
		this.trackingHistory = trackingHistory;
	}

}
