/* eslint-disable prettier/prettier */
import { Controller, Get, Param, Query, Res } from '@nestjs/common';
import { HotelsService } from './hotels.service';


@Controller('hotels')
export class HotelsController {
  constructor(private readonly hotelsService: HotelsService) {}

  @Get()
  async findAll(
    @Query("country") destination: string,
    @Query("arrivalDate") arrivalDate: Date,
    @Query("departureDate") departureDate: Date,
    @Query("limit") limit: number,
    @Res() res,
  ) {
    const hotels = await this.hotelsService.findAll(destination,arrivalDate, departureDate, limit);
    res.send(hotels);
  }
  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.hotelsService.findOne(+id);
  }

}
