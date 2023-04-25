/* eslint-disable prettier/prettier */
import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type FlightDocument = Flight & Document;

@Schema()
export class Flight {
  @Prop({ required: true })
  origin: string;

  @Prop({ required: true })
  destination: string;

  @Prop({ required: true })
  departureDate: string;

  @Prop({ required: true })
  departureTime: string;

  @Prop({ required: true })
  arrivalDate: string;

  @Prop({ required: true })
  arrivalTime: string;

  @Prop({ required: true })
  flightDuration: string;

  @Prop({ required: true })
  airline: string;

  @Prop({ required: true })
  flightNumber: string;

  @Prop({ required: true })
  price: number;

  @Prop({ required: true })
  imageUrl: string;

  @Prop({
    required: true,
    type: () => ({
      origin: String,
      destination: String,
      departureDate: String,
      departureTime: String,
      arrivalDate: String,
      arrivalTime: String,
      flightDuration: String,
      airline: String,
      flightNumber: String,
      price: Number,
      imageUrl: String,
    }),
  })
  returnFlight: {
    origin: string;
    destination: string;
    departureDate: string;
    departureTime: string;
    arrivalDate: string;
    arrivalTime: string;
    flightDuration: string;
    airline: string;
    flightNumber: string;
    price: number;
    imageUrl: string;
  };
}

export const FlightSchema = SchemaFactory.createForClass(Flight);
