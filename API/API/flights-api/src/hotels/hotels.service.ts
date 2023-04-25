/* eslint-disable prettier/prettier */
import { Injectable, InternalServerErrorException } from '@nestjs/common';
import { Hotel, HotelDocument } from './schema/hotel.schema';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose'

@Injectable()
export class HotelsService {

  constructor(@InjectModel(Hotel.name) private hotelModel: Model<HotelDocument>) {}

  async findAll(destination?: string, arrivalDate?: Date, departureDate?: Date, limit?: number): Promise<Hotel[]> {
    const query = {};

    if (destination) {
      query['country'] = destination;
    }
    
    if (arrivalDate) {
      query['available_dates.start_date'] = {
        $lte: new Date(arrivalDate)
      };
    }
    
    if (departureDate) {
      query['available_dates.end_date'] = {
        $gte: new Date(departureDate)
      };
    }
    
  
    try {
      console.log(query);
      debugger;
      const data = await this.hotelModel.find(query).limit(limit).exec();
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
