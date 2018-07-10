package net.querz.mcaselector.filter;

import net.querz.mcaselector.util.Debug;
import net.querz.mcaselector.version.VersionController;
import net.querz.mcaselector.version.anvil112.Anvil112ColorMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockFilter extends TextFilter<List<String>> {

	private static final Set<String> validNames = new HashSet<>();

	static {
		try (BufferedReader bis = new BufferedReader(
				new InputStreamReader(BlockFilter.class.getClass().getResourceAsStream("/block-names.csv")))) {
			String line;
			while ((line = bis.readLine()) != null) {
				validNames.add(line);
			}
		} catch (IOException ex) {
			Debug.error("error reading block-names.csv: ", ex.getMessage());
		}
	}

	public BlockFilter() {
		this(Operator.AND, Comparator.CONTAINS, null);
	}

	public BlockFilter(Operator operator, Comparator comparator, List<String> value) {
		super(FilterType.BLOCK, operator, comparator, value);
		setRawValue(String.join(",", value == null ? new ArrayList<>(0) : value));
	}

	@Override
	public boolean contains(List<String> value, FilterData data) {
		return VersionController.getChunkFilter(data.getChunk().getInt("DataVersion")).matchBlockNames(data.getChunk(), value.toArray(new String[0]));
	}

	@Override
	public boolean containsNot(List<String> value, FilterData data) {
		return !contains(value, data);
	}

	@Override
	public void setFilterValue(String raw) {
		String[] rawBlockNames = raw.split(",");
		if (raw.isEmpty() || rawBlockNames.length == 0) {
			setValid(false);
			setValue(null);
		} else {
			for (int i = 0; i < rawBlockNames.length; i++) {
				String name = rawBlockNames[i];
				if (!validNames.contains(name)) {
					if (name.startsWith("\"") && name.endsWith("\"") && name.length() >= 2) {
						rawBlockNames[i] = name.substring(1, name.length() - 1);
						continue;
					}
					setValue(null);
					setValid(false);
					return;
				}
			}
			setValid(true);
			setValue(Arrays.asList(rawBlockNames));
			setRawValue(raw);
		}
	}

	@Override
	public String getFormatText() {
		return "<block>[,<block>,...]";
	}

	@Override
	public String toString(FilterData data) {
		return "";
	}

	@Override
	public String toString() {
		return "Blocks " + getComparator() + " " + (getFilterValue() != null ? Arrays.toString(getFilterValue().toArray()) : "null");
	}
}
