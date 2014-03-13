package us.kbase.tree;

import java.util.Map;

import us.kbase.common.taskqueue.TaskQueueConfig;
import us.kbase.common.taskqueue.TaskRunner;

public class SpeciesTreeBuilder implements TaskRunner<ConstructSpeciesTreeParams> {
	
	@Override
	public Class<ConstructSpeciesTreeParams> getInputDataType() {
		return ConstructSpeciesTreeParams.class;
	}

	@Override
	public String getOutRef(ConstructSpeciesTreeParams inputData) {
		return null;
	}
	
	@Override
	public String getTaskDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void init(TaskQueueConfig mainCfg, Map<String, String> configParams) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run(String token, ConstructSpeciesTreeParams inputData,
			String jobId, String outRef) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
