package com.alibaba.jstorm.container.cgroup.core;

import java.io.IOException;
import java.util.LinkedList;

import com.alibaba.jstorm.container.CgroupUtils;
import com.alibaba.jstorm.container.Constants;
import com.alibaba.jstorm.container.SubSystemType;

public class CpusetCore implements CgroupCore {

	public static final String CPUSET_CPUS = "/cpuset.cpus";
	public static final String CPUSET_MEMS = "/cpuset.mems";
	public static final String CPUSET_MEMORY_MIGRATE = "/cpuset.memory_migrate";
	public static final String CPUSET_CPU_EXCLUSIVE = "/cpuset.cpu_exclusive";
	public static final String CPUSET_MEM_EXCLUSIVE = "/cpuset.mem_exclusive";
	public static final String CPUSET_MEM_HARDWALL = "/cpuset.mem_hardwall";
	public static final String CPUSET_MEMORY_PRESSURE = "/cpuset.memory_pressure";
	public static final String CPUSET_MEMORY_PRESSURE_ENABLED = "/cpuset.memory_pressure_enabled";
	public static final String CPUSET_MEMORY_SPREAD_PAGE = "/cpuset.memory_spread_page";
	public static final String CPUSET_MEMORY_SPREAD_SLAB = "/cpuset.memory_spread_slab";
	public static final String CPUSET_SCHED_LOAD_BALANCE = "/cpuset.sched_load_balance";
	public static final String CPUSET_SCHED_RELAX_DOMAIN_LEVEL = "/cpuset.sched_relax_domain_level";

	private final String dir;

	public CpusetCore(String dir) {
		this.dir = dir;
	}

	@Override
	public SubSystemType getType() {
		// TODO Auto-generated method stub
		return SubSystemType.cpuset;
	}

	public void setCpus(int[] nums) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int num : nums) {
			sb.append(num);
			sb.append(',');
		}
		sb.deleteCharAt(sb.length() - 1);
		CgroupUtils.writeFileByLine(Constants.getDir(this.dir, CPUSET_CPUS),
				sb.toString());
	}

	public int[] getCpus() throws IOException {
		String output = CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_CPUS)).get(0);
		return parseNums(output);
	}

	public void setMems(int[] nums) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int num : nums) {
			sb.append(num);
			sb.append(',');
		}
		sb.deleteCharAt(sb.length() - 1);
		CgroupUtils.writeFileByLine(Constants.getDir(this.dir, CPUSET_MEMS),
				sb.toString());
	}

	public int[] getMems() throws IOException {
		String output = CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMS)).get(0);
		return parseNums(output);
	}

	public void setMemMigrate(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_MIGRATE),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isMemMigrate() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_MIGRATE)).get(0));
		return output > 0;
	}

	public void setCpuExclusive(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_CPU_EXCLUSIVE),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isCpuExclusive() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_CPU_EXCLUSIVE)).get(0));
		return output > 0;
	}

	public void setMemExclusive(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_MEM_EXCLUSIVE),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isMemExclusive() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEM_EXCLUSIVE)).get(0));
		return output > 0;
	}

	public void setMemHardwall(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_MEM_HARDWALL),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isMemHardwall() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEM_HARDWALL)).get(0));
		return output > 0;
	}

	public int getMemPressure() throws IOException {
		String output = CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_PRESSURE)).get(0);
		return Integer.parseInt(output);
	}

	public void setMemPressureEnabled(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_PRESSURE_ENABLED),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isMemPressureEnabled() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_PRESSURE_ENABLED))
				.get(0));
		return output > 0;
	}

	public void setMemSpreadPage(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_SPREAD_PAGE),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isMemSpreadPage() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_SPREAD_PAGE)).get(0));
		return output > 0;
	}

	public void setMemSpreadSlab(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_SPREAD_SLAB),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isMemSpreadSlab() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_MEMORY_SPREAD_SLAB)).get(0));
		return output > 0;
	}

	public void setSchedLoadBlance(boolean flag) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_SCHED_LOAD_BALANCE),
				String.valueOf(flag ? 1 : 0));
	}

	public boolean isSchedLoadBlance() throws IOException {
		int output = Integer.parseInt(CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_SCHED_LOAD_BALANCE)).get(0));
		return output > 0;
	}

	public void setSchedRelaxDomainLevel(int value) throws IOException {
		CgroupUtils.writeFileByLine(
				Constants.getDir(this.dir, CPUSET_SCHED_RELAX_DOMAIN_LEVEL),
				String.valueOf(value));
	}

	public int getSchedRelaxDomainLevel() throws IOException {
		String output = CgroupUtils.readFileByLine(
				Constants.getDir(this.dir, CPUSET_SCHED_RELAX_DOMAIN_LEVEL))
				.get(0);
		return Integer.parseInt(output);
	}

	public static int[] parseNums(String outputStr) {
		char[] output = outputStr.toCharArray();
		LinkedList<Integer> numList = new LinkedList<Integer>();
		int value = 0;
		int start = 0;
		boolean isHyphen = false;
		for (char ch : output) {
			if (ch == ',') {
				if (isHyphen) {
					for (; start <= value; start++) {
						numList.add(start);
					}
					isHyphen = false;
				} else {
					numList.add(value);
				}
				value = 0;
			} else if (ch == '-') {
				isHyphen = true;
				start = value;
				value = 0;
			} else {
				value = value * 10 + (ch - '0');
			}
		}
		if (output[output.length - 1] != ',') {
			if (isHyphen) {
				for (; start <= value; start++) {
					numList.add(start);
				}
			} else {
				numList.add(value);
			}
		}

		int[] nums = new int[numList.size()];
		int index = 0;
		for (int num : numList) {
			nums[index] = num;
			index++;
		}

		return nums;
	}

}
