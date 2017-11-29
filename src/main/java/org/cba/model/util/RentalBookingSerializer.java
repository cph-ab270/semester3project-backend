package org.cba.model.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.cba.model.entities.Booking;
import org.cba.model.entities.Rental;

import java.io.IOException;

/**
 * Created by adam on 29/11/2017.
 */
public class RentalBookingSerializer extends StdSerializer<Rental> {

    public RentalBookingSerializer() {
        this(null);
    }

    public RentalBookingSerializer(Class<Rental> bookingClass) {
        super(bookingClass);
    }

    @Override
    public void serialize(Rental rental, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        WeekDateFormatter formatter = new WeekDateFormatter();
        jgen.writeStartArray();
        for (Booking booking : rental.getBookings()) {
            jgen.writeStartObject();
            jgen.writeStringField("userName", booking.getUser().getUsername());
            jgen.writeStringField("week", formatter.format(booking.getWeek()));
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

}
