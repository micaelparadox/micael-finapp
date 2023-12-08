# Use the official Node.js 16 image.
FROM node:16

# Create and change to the app directory.
WORKDIR /app

# Update the package list and install the MySQL client.
RUN apt-get update && apt-get install -y default-mysql-client

# Install ts-node-dev globally.
RUN npm install -g ts-node-dev

# Copy application dependency manifests to the container image.
COPY package*.json ./

# Install all dependencies (including devDependencies).
RUN npm install

# Copy local code to the container image.
COPY . .

# Compile TypeScript to JavaScript.
RUN npm run build

# Copy .env file to the container (assuming it's in the same directory as Dockerfile).
COPY .env .env

# Define the DATABASE_URL environment variable.
ENV DATABASE_URL="mysql://user:rootpassword@micael-finapp-db-1:3306/micael_finapp"

# Run the web service on container startup using ts-node-dev for development.
CMD [ "npx", "ts-node-dev", "--respawn", "--transpile-only", "src/server.ts" ]

# Service runs on port 3000.
EXPOSE 3000
