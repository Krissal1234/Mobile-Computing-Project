/* eslint-disable prettier/prettier */
import { Controller, Get, Param, Query, Res } from '@nestjs/common';
import { FlightsService } from './flights.service';


@Controller('flights')
export class FlightsController {
  constructor(private readonly flightsService: FlightsService) {}

  @Get()
  async findAll(
    @Query("origin") origin: string,
    @Query("departureDate") departureDate: string,
    @Query("returnDate") returnDate: string,
    @Query("limit") limit: number,
    @Res() res,
  ) {
    const flights = await this.flightsService.findAll(origin, departureDate, returnDate, limit);
    res.send(flights);
  }
  

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.flightsService.findOne(+id);
  }

}
