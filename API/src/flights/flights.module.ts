/* eslint-disable prettier/prettier */
import { Module } from '@nestjs/common';
import { FlightsService } from './flights.service';
import { FlightsController } from './flights.controller';
import { MongooseModule } from '@nestjs/mongoose';
import { Flight, FlightSchema } from './schema/flight.schema';

@Module({
  imports: [
    MongooseModule.forFeature([{ name: Flight.name, schema: FlightSchema }]),
  ],
  controllers: [FlightsController],
  providers: [FlightsService],
  exports: [FlightsService]
})
export class FlightsModule {}
