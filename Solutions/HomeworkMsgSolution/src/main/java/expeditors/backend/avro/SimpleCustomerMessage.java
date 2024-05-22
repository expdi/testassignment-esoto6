/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package expeditors.backend.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SimpleCustomerMessage extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 9052438745157541892L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SimpleCustomerMessage\",\"namespace\":\"expeditors.backend.avro\",\"fields\":[{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Name of Customer\",\"default\":\"\"},{\"name\":\"dob\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Date of Birth\",\"default\":\"\"},{\"name\":\"status\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Status\",\"default\":\"\"},{\"name\":\"time_stamp\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Datetime when the message was generated\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SimpleCustomerMessage> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SimpleCustomerMessage> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SimpleCustomerMessage> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SimpleCustomerMessage> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SimpleCustomerMessage> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SimpleCustomerMessage to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SimpleCustomerMessage from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SimpleCustomerMessage instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SimpleCustomerMessage fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Name of Customer */
  private java.lang.String name;
  /** Date of Birth */
  private java.lang.String dob;
  /** Status */
  private java.lang.String status;
  /** Datetime when the message was generated */
  private java.lang.String time_stamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SimpleCustomerMessage() {}

  /**
   * All-args constructor.
   * @param name Name of Customer
   * @param dob Date of Birth
   * @param status Status
   * @param time_stamp Datetime when the message was generated
   */
  public SimpleCustomerMessage(java.lang.String name, java.lang.String dob, java.lang.String status, java.lang.String time_stamp) {
    this.name = name;
    this.dob = dob;
    this.status = status;
    this.time_stamp = time_stamp;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return name;
    case 1: return dob;
    case 2: return status;
    case 3: return time_stamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: name = value$ != null ? value$.toString() : null; break;
    case 1: dob = value$ != null ? value$.toString() : null; break;
    case 2: status = value$ != null ? value$.toString() : null; break;
    case 3: time_stamp = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'name' field.
   * @return Name of Customer
   */
  public java.lang.String getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * Name of Customer
   * @param value the value to set.
   */
  public void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'dob' field.
   * @return Date of Birth
   */
  public java.lang.String getDob() {
    return dob;
  }


  /**
   * Sets the value of the 'dob' field.
   * Date of Birth
   * @param value the value to set.
   */
  public void setDob(java.lang.String value) {
    this.dob = value;
  }

  /**
   * Gets the value of the 'status' field.
   * @return Status
   */
  public java.lang.String getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * Status
   * @param value the value to set.
   */
  public void setStatus(java.lang.String value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'time_stamp' field.
   * @return Datetime when the message was generated
   */
  public java.lang.String getTimeStamp() {
    return time_stamp;
  }


  /**
   * Sets the value of the 'time_stamp' field.
   * Datetime when the message was generated
   * @param value the value to set.
   */
  public void setTimeStamp(java.lang.String value) {
    this.time_stamp = value;
  }

  /**
   * Creates a new SimpleCustomerMessage RecordBuilder.
   * @return A new SimpleCustomerMessage RecordBuilder
   */
  public static expeditors.backend.avro.SimpleCustomerMessage.Builder newBuilder() {
    return new expeditors.backend.avro.SimpleCustomerMessage.Builder();
  }

  /**
   * Creates a new SimpleCustomerMessage RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SimpleCustomerMessage RecordBuilder
   */
  public static expeditors.backend.avro.SimpleCustomerMessage.Builder newBuilder(expeditors.backend.avro.SimpleCustomerMessage.Builder other) {
    if (other == null) {
      return new expeditors.backend.avro.SimpleCustomerMessage.Builder();
    } else {
      return new expeditors.backend.avro.SimpleCustomerMessage.Builder(other);
    }
  }

  /**
   * Creates a new SimpleCustomerMessage RecordBuilder by copying an existing SimpleCustomerMessage instance.
   * @param other The existing instance to copy.
   * @return A new SimpleCustomerMessage RecordBuilder
   */
  public static expeditors.backend.avro.SimpleCustomerMessage.Builder newBuilder(expeditors.backend.avro.SimpleCustomerMessage other) {
    if (other == null) {
      return new expeditors.backend.avro.SimpleCustomerMessage.Builder();
    } else {
      return new expeditors.backend.avro.SimpleCustomerMessage.Builder(other);
    }
  }

  /**
   * RecordBuilder for SimpleCustomerMessage instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SimpleCustomerMessage>
    implements org.apache.avro.data.RecordBuilder<SimpleCustomerMessage> {

    /** Name of Customer */
    private java.lang.String name;
    /** Date of Birth */
    private java.lang.String dob;
    /** Status */
    private java.lang.String status;
    /** Datetime when the message was generated */
    private java.lang.String time_stamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(expeditors.backend.avro.SimpleCustomerMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.dob)) {
        this.dob = data().deepCopy(fields()[1].schema(), other.dob);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.time_stamp)) {
        this.time_stamp = data().deepCopy(fields()[3].schema(), other.time_stamp);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing SimpleCustomerMessage instance
     * @param other The existing instance to copy.
     */
    private Builder(expeditors.backend.avro.SimpleCustomerMessage other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.name)) {
        this.name = data().deepCopy(fields()[0].schema(), other.name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.dob)) {
        this.dob = data().deepCopy(fields()[1].schema(), other.dob);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.time_stamp)) {
        this.time_stamp = data().deepCopy(fields()[3].schema(), other.time_stamp);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'name' field.
      * Name of Customer
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * Name of Customer
      * @param value The value of 'name'.
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder setName(java.lang.String value) {
      validate(fields()[0], value);
      this.name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * Name of Customer
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'name' field.
      * Name of Customer
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder clearName() {
      name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'dob' field.
      * Date of Birth
      * @return The value.
      */
    public java.lang.String getDob() {
      return dob;
    }


    /**
      * Sets the value of the 'dob' field.
      * Date of Birth
      * @param value The value of 'dob'.
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder setDob(java.lang.String value) {
      validate(fields()[1], value);
      this.dob = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'dob' field has been set.
      * Date of Birth
      * @return True if the 'dob' field has been set, false otherwise.
      */
    public boolean hasDob() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'dob' field.
      * Date of Birth
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder clearDob() {
      dob = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * Status
      * @return The value.
      */
    public java.lang.String getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * Status
      * @param value The value of 'status'.
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder setStatus(java.lang.String value) {
      validate(fields()[2], value);
      this.status = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * Status
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'status' field.
      * Status
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder clearStatus() {
      status = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'time_stamp' field.
      * Datetime when the message was generated
      * @return The value.
      */
    public java.lang.String getTimeStamp() {
      return time_stamp;
    }


    /**
      * Sets the value of the 'time_stamp' field.
      * Datetime when the message was generated
      * @param value The value of 'time_stamp'.
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder setTimeStamp(java.lang.String value) {
      validate(fields()[3], value);
      this.time_stamp = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'time_stamp' field has been set.
      * Datetime when the message was generated
      * @return True if the 'time_stamp' field has been set, false otherwise.
      */
    public boolean hasTimeStamp() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'time_stamp' field.
      * Datetime when the message was generated
      * @return This builder.
      */
    public expeditors.backend.avro.SimpleCustomerMessage.Builder clearTimeStamp() {
      time_stamp = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SimpleCustomerMessage build() {
      try {
        SimpleCustomerMessage record = new SimpleCustomerMessage();
        record.name = fieldSetFlags()[0] ? this.name : (java.lang.String) defaultValue(fields()[0]);
        record.dob = fieldSetFlags()[1] ? this.dob : (java.lang.String) defaultValue(fields()[1]);
        record.status = fieldSetFlags()[2] ? this.status : (java.lang.String) defaultValue(fields()[2]);
        record.time_stamp = fieldSetFlags()[3] ? this.time_stamp : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SimpleCustomerMessage>
    WRITER$ = (org.apache.avro.io.DatumWriter<SimpleCustomerMessage>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SimpleCustomerMessage>
    READER$ = (org.apache.avro.io.DatumReader<SimpleCustomerMessage>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.name);

    out.writeString(this.dob);

    out.writeString(this.status);

    out.writeString(this.time_stamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.name = in.readString();

      this.dob = in.readString();

      this.status = in.readString();

      this.time_stamp = in.readString();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.name = in.readString();
          break;

        case 1:
          this.dob = in.readString();
          break;

        case 2:
          this.status = in.readString();
          break;

        case 3:
          this.time_stamp = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










