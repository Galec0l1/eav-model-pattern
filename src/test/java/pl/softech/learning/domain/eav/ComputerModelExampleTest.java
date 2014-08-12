package pl.softech.learning.domain.eav;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.softech.learning.HSqlConfig;
import pl.softech.learning.domain.dictionary.Dictionary;
import pl.softech.learning.domain.dictionary.DictionaryEntry;
import pl.softech.learning.domain.dictionary.DictionaryEntryIdentifier;
import pl.softech.learning.domain.dictionary.DictionaryEntryRepository;
import pl.softech.learning.domain.dictionary.DictionaryIdentifier;
import pl.softech.learning.domain.dictionary.DictionaryRepository;
import pl.softech.learning.domain.eav.Attribute.DataType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HSqlConfig.class)
public class ComputerModelExampleTest {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AttributeRepository attributeRepository;
	@Autowired
	private ObjectValueRepository objectValueRepository;
	@Autowired
	private MyObjectRepository myObjectRepository;
	@Autowired
	private DictionaryRepository dictionaryRepository;
	@Autowired
	private DictionaryEntryRepository dictionaryEntryRepository;

	private DictionaryIdentifier computerType;
	private DictionaryIdentifier os;
	private DictionaryIdentifier computerMake;
	private DictionaryEntryIdentifier notebook;
	private DictionaryEntryIdentifier desktop;
	private DictionaryEntryIdentifier win7;
	private DictionaryEntryIdentifier win8;
	private DictionaryEntryIdentifier linux;
	private DictionaryEntryIdentifier solaris;
	private DictionaryEntryIdentifier dell;
	private DictionaryEntryIdentifier lenovo;
	private DictionaryEntryIdentifier apple;

	CategoryIdentifier computerCategory;

	@Before
	public void init() {

		computerType = new DictionaryIdentifier("computer_type");
		os = new DictionaryIdentifier("os");
		computerMake = new DictionaryIdentifier("computer_make");

		dictionaryRepository.save(Arrays.asList(//
				new Dictionary(computerType, "Computer Type"),//
				new Dictionary(os, "Operating System"),//
				new Dictionary(computerMake, "Computer Make")//
				));

		notebook = new DictionaryEntryIdentifier("notebook");
		desktop = new DictionaryEntryIdentifier("desktop");
		win7 = new DictionaryEntryIdentifier("win7");
		win8 = new DictionaryEntryIdentifier("win8");
		linux = new DictionaryEntryIdentifier("linux");
		solaris = new DictionaryEntryIdentifier("solaris");
		dell = new DictionaryEntryIdentifier("dell");
		lenovo = new DictionaryEntryIdentifier("lenovo");
		apple = new DictionaryEntryIdentifier("apple");

		dictionaryEntryRepository.save(Arrays.asList(//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(computerType), notebook, "Notebook"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(computerType), desktop, "Desktop"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(os), win7, "Windows 7"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(os), win8, "Window 8"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(os), linux, "Linux"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(os), solaris, "Solaris"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(computerMake), dell, "Dell"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(computerMake), lenovo, "Lenovo"),//
				new DictionaryEntry(dictionaryRepository.findByIdentifier(computerMake), apple, "Apple")//
				));

		computerCategory = new CategoryIdentifier("computer");
		Category computer = new Category(computerCategory, "Computer");
		categoryRepository.save(computer);

		attributeRepository.save(new Attribute(new AttributeIdentifier("make"), "Make", computer, DataType.DICTIONARY));
		attributeRepository.save(new Attribute(new AttributeIdentifier("model"), "Model", computer, DataType.TEXT));
		attributeRepository.save(new Attribute(new AttributeIdentifier("type"), "Type", computer, DataType.DICTIONARY));
		attributeRepository.save(new Attribute(new AttributeIdentifier("cpu"), "CPU", computer, DataType.TEXT));
		attributeRepository.save(new Attribute(new AttributeIdentifier("drive"), "Drive", computer, DataType.TEXT));
		attributeRepository.save(new Attribute(new AttributeIdentifier("video"), "Video", computer, DataType.TEXT));
		attributeRepository.save(new Attribute(new AttributeIdentifier("ram"), "RAM (GB)", computer, DataType.INTEGER));
		attributeRepository.save(new Attribute(new AttributeIdentifier("optical"), "Optical", computer, DataType.TEXT));
		attributeRepository.save(new Attribute(new AttributeIdentifier("battery"), "Battery", computer, DataType.TEXT));
		attributeRepository.save(new Attribute(new AttributeIdentifier("screen"), "Screen", computer, DataType.TEXT));
		attributeRepository.save(new Attribute(new AttributeIdentifier("os"), "OS", computer, DataType.DICTIONARY));
		attributeRepository.save(new Attribute(new AttributeIdentifier("purshase_date"), "Purschase Date", computer, DataType.DATE));
	}

	@Test
	@Transactional
	public void testExample() {

		MyObject computer = new MyObject(categoryRepository.findByIdentifier(computerCategory), "MAUI");

		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("make")), new DictionaryEntryValue(
				dictionaryEntryRepository.findByIdentifier(computerMake, dell)));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("model")), new StringValue("Studio15"));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("type")), new DictionaryEntryValue(
				dictionaryEntryRepository.findByIdentifier(computerType, notebook)));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("cpu")), new StringValue("Core 2 Duo 2.4GHz"));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("drive")), new StringValue("320Gb 5400rpm"));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("video")), new StringValue("Intel Acc"));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("ram")), new IntegerValue(4));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("optical")), new StringValue("DVD RW"));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("battery")), new StringValue("6 cell"));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("screen")), new StringValue("15\""));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("os")), new DictionaryEntryValue(
				dictionaryEntryRepository.findByIdentifier(os, win7)));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("os")), new DictionaryEntryValue(
				dictionaryEntryRepository.findByIdentifier(os, linux)));
		computer.addValue(attributeRepository.findByIdentifier(new AttributeIdentifier("purshase_date")), new DateValue(new Date()));

		myObjectRepository.save(computer);

		MyObject obj = myObjectRepository.findByName("MAUI");

		ObjectValue makeValue = obj.getValueByAttribute(new AttributeIdentifier("make"));
		Assert.assertEquals("Dell", makeValue.getValueAsString());

		ObjectValue ramValue = obj.getValueByAttribute(new AttributeIdentifier("ram"));
		Assert.assertEquals("4", ramValue.getValueAsString());

		Assert.assertFalse(obj.hasValues(new AttributeIdentifier("ramm")));
		Assert.assertTrue(obj.getValuesByAttribute((new AttributeIdentifier("ramm"))).isEmpty());

		try {
			obj.getValueByAttribute((new AttributeIdentifier("ramm")));
			Assert.fail();
		} catch (Exception e) {
			
		}

		Set<ObjectValue> opticals = obj.getValuesByAttribute(new AttributeIdentifier("optical"));
		Assert.assertEquals(1, opticals.size());
		Assert.assertEquals("DVD RW", opticals.iterator().next().getValueAsString());

		Set<ObjectValue> oss = obj.getValuesByAttribute(new AttributeIdentifier("os"));
		Assert.assertEquals(2, oss.size());

		ObjectValue[] arr = oss.toArray(new ObjectValue[2]);
		Arrays.sort(arr, new Comparator<ObjectValue>() {

			@Override
			public int compare(ObjectValue o1, ObjectValue o2) {
				return o1.getValueAsString().compareTo(o2.getValueAsString());
			}
		});
		Assert.assertEquals("Linux", arr[0].getValueAsString());
		Assert.assertEquals("Windows 7", arr[1].getValueAsString());

	}

}