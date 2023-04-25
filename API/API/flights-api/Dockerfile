# Use the official Node.js runtime as a parent image
FROM node:14

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install NestJS and other dependencies
RUN npm install

# Expose the port the application will run on
EXPOSE 3000

# Start the application
CMD ["npm", "start"]
