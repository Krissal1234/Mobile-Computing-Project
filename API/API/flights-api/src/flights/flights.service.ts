/* eslint-disable prettier/prettier */
import { Injectable, InternalServerErrorException } from '@nestjs/common';
import { Flight, FlightDocument } from './schema/flight.schema';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose'

@Injectable()
export class FlightsService {
  constructor(@InjectModel(Flight.name) private flightModel: Model<FlightDocument>) {}

  async findAll(origin?: string, departureDate?: string, arrivalDate?: string, limit?: number): Promise<Flight[]> {
    const query = {};
    if (origin) {
      query['origin'] = origin;
    }
    if (departureDate) {
      query['departureDate'] = departureDate;
    }
    if (arrivalDate) {
      query['return.arrivalDate'] = arrivalDate;
    }
    
    try {
      const data = await this.flightModel.find(query).limit(limit).exec();
      console.log(data);
      return data;
    } catch (error) {
      console.log(error);
      throw new InternalServerErrorException();
    }
  }
  

  findOne(id: number) {
    return `This action returns a #${id} flight`;
  }

}
