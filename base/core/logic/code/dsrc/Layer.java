import java.util.ArrayList;
import java.util.List;


public class Layer {

	private String targetBizLayer;
	private String targetBizTables;
	private List<String> targetOutFields = new ArrayList<String>();
	private Object outSpatialReference ;
	private Boolean returnOverlayGeometry ;
	private Boolean returnOverlayPartition;
	private Boolean returnOverlayArea;
	private Boolean returnOutsideGeometry;
	private Boolean returnOutsidePartition;
	private Boolean returnOutsideArea;
	private Boolean returnSourceFeatureGeometry;
	private Boolean returnTargetFeatureGeometry;
	private Boolean returnOverlaySpheroidArea;
	private Boolean returnOutsideSpheroidArea;
	
	public String getTargetBizLayer() {
		return targetBizLayer;
	}
	public void setTargetBizLayer(String bizLayerName) {
		this.targetBizLayer = bizLayerName;
	}
	public String getTargetBizTables() {
		return targetBizTables;
	}
	public void setTargetBizTables(String filter) {
		this.targetBizTables = filter;
	}
	public List<String> getTargetOutFields() {
		return targetOutFields;
	}
	public void setTargetOutFields(List<String> targetOutFields) {
		this.targetOutFields = targetOutFields;
	}
	public Object getOutSpatialReference() {
		return outSpatialReference;
	}
	public void setOutSpatialReference(Object wkid) {
		this.outSpatialReference = wkid;
	}
	public Boolean getReturnOverlayGeometry() {
		return returnOverlayGeometry;
	}
	public void setReturnOverlayGeometry(Boolean returnOverlayGemetry) {
		this.returnOverlayGeometry = returnOverlayGemetry;
	}
	public Boolean getReturnOverlayPartition() {
		return returnOverlayPartition;
	}
	public void setReturnOverlayPartition(Boolean returnOverlayPartition) {
		this.returnOverlayPartition = returnOverlayPartition;
	}
	public Boolean getReturnOverlayArea() {
		return returnOverlayArea;
	}
	public void setReturnOverlayArea(Boolean returnOverlayArea) {
		this.returnOverlayArea = returnOverlayArea;
	}
	public Boolean getReturnOutsideGeometry() {
		return returnOutsideGeometry;
	}
	public void setReturnOutsideGeometry(Boolean returnOutsideGeometry) {
		this.returnOutsideGeometry = returnOutsideGeometry;
	}
	public Boolean getReturnOutsidePartition() {
		return returnOutsidePartition;
	}
	public void setReturnOutsidePartition(Boolean returnOutsidePartition) {
		this.returnOutsidePartition = returnOutsidePartition;
	}
	public Boolean getReturnOutsideArea() {
		return returnOutsideArea;
	}
	public void setReturnOutsideArea(Boolean returnOutsideArea) {
		this.returnOutsideArea = returnOutsideArea;
	}
	public Boolean getReturnSourceFeatureGeometry() {
		return returnSourceFeatureGeometry;
	}
	public void setReturnSourceFeatureGeometry(Boolean returnSourceFeatureGeometry) {
		this.returnSourceFeatureGeometry = returnSourceFeatureGeometry;
	}
	public Boolean getReturnTargetFeatureGeometry() {
		return returnTargetFeatureGeometry;
	}
	public void setReturnTargetFeatureGeometry(Boolean returnTargetFeatureGeometry) {
		this.returnTargetFeatureGeometry = returnTargetFeatureGeometry;
	}
	public Boolean getReturnOverlaySpheroidArea() {
		return returnOverlaySpheroidArea;
	}
	public void setReturnOverlaySpheroidArea(Boolean returnOverlaySpheroidArea) {
		this.returnOverlaySpheroidArea = returnOverlaySpheroidArea;
	}
	public Boolean getReturnOutsideSpheroidArea() {
		return returnOutsideSpheroidArea;
	}
	public void setReturnOutsideSpheroidArea(Boolean returnOutsideSpheroidArea) {
		this.returnOutsideSpheroidArea = returnOutsideSpheroidArea;
	}
}
