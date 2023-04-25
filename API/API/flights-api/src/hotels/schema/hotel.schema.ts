/* eslint-disable prettier/prettier */
import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';


@Schema()
export class Hotel {

  @Prop({ required: true })
  _id: string;

  @Prop({ required: true })
  country: string; 

  @Prop({ required: true })
  city: string;

  @Prop({ required: true })
  hotel_name: string;

  @Prop({ required: true })
  price_per_night: number;

  @Prop({ required: true })
  address: string;

  @Prop({type: [{ start_date: Date, end_date: Date }], required: true})
  available_dates: { start_date: Date, end_date: Date }[];


}

export type HotelDocument = Hotel & Document;
export const HotelSchema = SchemaFactory.createForClass(Hotel);
