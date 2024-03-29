/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 */
package org.biojava.nbio.genome.io.fastq;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract unit test for implementations of FastqWriter.
 */
public abstract class AbstractFastqWriterTest {

	/**
	 * Create and return a new FASTQ formatted sequence suitable for testing.
	 *
	 * @return a new FASTQ formatted sequence suitable for testing.
	 */
	protected abstract Fastq createFastq();

	/**
	 * Create and return a new instance of an implementation of FastqWriter to test.
	 *
	 * @return a new instance of an implementation of FastqWriter to test
	 */
	protected abstract FastqWriter createFastqWriter();

	@Test
	public void testCreateFastq()
	{
		Fastq fastq = createFastq();
		Assert.assertNotNull(fastq);
	}

	@Test
	public void testCreateFastqWriter()
	{
		FastqWriter writer = createFastqWriter();
		Assert.assertNotNull(writer);
	}

	@Test
	public void testAppendVararg() throws Exception
	{
		FastqWriter writer = createFastqWriter();
		Appendable appendable = new StringBuilder();
		Fastq fastq0 = createFastq();
		Fastq fastq1 = createFastq();
		Fastq fastq2 = createFastq();
		Assert.assertSame(appendable, writer.append(appendable, fastq0));
		Assert.assertSame(appendable, writer.append(appendable, fastq0, fastq1));
		Assert.assertSame(appendable, writer.append(appendable, fastq0, fastq1, fastq2));
		Assert.assertSame(appendable, writer.append(appendable, fastq0, fastq1, fastq2, null));
		Assert.assertSame(appendable, writer.append(appendable, (Fastq) null));

		try
		{
			writer.append((Appendable) null, fastq0);
			Assert.fail("append(null,) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testAppendIterable() throws Exception
	{
		FastqWriter writer = createFastqWriter();
		Appendable appendable = new StringBuilder();
		Fastq fastq0 = createFastq();
		Fastq fastq1 = createFastq();
		Fastq fastq2 = createFastq();
		List<Fastq> list = new ArrayList<Fastq>();
		Assert.assertSame(appendable, writer.append(appendable, list));
		list.add(fastq0);
		Assert.assertSame(appendable, writer.append(appendable, list));
		list.add(fastq1);
		Assert.assertSame(appendable, writer.append(appendable, list));
		list.add(fastq2);
		Assert.assertSame(appendable, writer.append(appendable, list));
		list.add(null);
		Assert.assertSame(appendable, writer.append(appendable, list));

		try
		{
			writer.append((Appendable) null, list);
			Assert.fail("append(null,) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
		try
		{
			writer.append(appendable, (Iterable<Fastq>) null);
			Assert.fail("append(,null) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testWriteFileVararg() throws Exception
	{
		FastqWriter writer = createFastqWriter();
		Fastq fastq0 = createFastq();
		Fastq fastq1 = createFastq();
		Fastq fastq2 = createFastq();
		File file0 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file0, fastq0);
		File file1 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file1, fastq0, fastq1);
		File file2 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file2, fastq0, fastq1, fastq2);
		File file3 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file3, fastq0, fastq1, fastq2, null);
		File file4 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file4, (Fastq) null);

		try
		{
			writer.write((File) null, fastq0);
			Assert.fail("append(null,) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testWriteFileIterable() throws Exception
	{
		FastqWriter writer = createFastqWriter();
		Fastq fastq0 = createFastq();
		Fastq fastq1 = createFastq();
		Fastq fastq2 = createFastq();
		List<Fastq> list = new ArrayList<Fastq>();
		File file0 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file0, list);

		list.add(fastq0);
		File file1 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file1, list);

		list.add(fastq1);
		File file2 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file2, list);

		list.add(fastq2);
		File file3 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file3, list);

		list.add(null);
		File file4 = Files.createTempFile("abstractFastqWriterTest",null).toFile();
		writer.write(file4, list);

		File file5 = Files.createTempFile("abstractFastqWriterTest",null).toFile();

		try
		{
			writer.write((File) null, fastq0);
			Assert.fail("append(null,) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
		try
		{
			writer.write(file5, (Iterable<Fastq>) null);
			Assert.fail("append(,null) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testWriteOutputStreamVararg() throws Exception
	{
		FastqWriter writer = createFastqWriter();
		Fastq fastq0 = createFastq();
		Fastq fastq1 = createFastq();
		Fastq fastq2 = createFastq();
		OutputStream outputStream = new ByteArrayOutputStream();
		writer.write(outputStream, fastq0);
		writer.write(outputStream, fastq0, fastq1);
		writer.write(outputStream, fastq0, fastq1, fastq2);
		writer.write(outputStream, fastq0, fastq1, fastq2, null);
		writer.write(outputStream, (Fastq) null);

		try
		{
			writer.write((OutputStream) null, fastq0);
			Assert.fail("append(null,) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}

	@Test
	public void testWriteOutputStreamIterable() throws Exception
	{
		FastqWriter writer = createFastqWriter();
		Fastq fastq0 = createFastq();
		Fastq fastq1 = createFastq();
		Fastq fastq2 = createFastq();
		OutputStream outputStream = new ByteArrayOutputStream();
		List<Fastq> list = new ArrayList<Fastq>();
		writer.write(outputStream, list);
		list.add(fastq0);
		writer.write(outputStream, list);
		list.add(fastq1);
		writer.write(outputStream, list);
		list.add(fastq2);
		writer.write(outputStream, list);
		list.add(null);
		writer.write(outputStream, list);

		try
		{
			writer.write((OutputStream) null, fastq0);
			Assert.fail("append(null,) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
		try
		{
			writer.write(outputStream, (Iterable<Fastq>) null);
			Assert.fail("append(,null) expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e)
		{
			// expected
		}
	}
}
