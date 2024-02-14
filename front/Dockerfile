FROM node:lts AS build

# Set the working directory
WORKDIR /usr/local/app

# Add the source code to app
COPY ./ /usr/local/app/

# Install all the dependencies
RUN npm install

# Generate the build of the application
RUN npm run build


# Stage 2: Serve app with nginx server

# Use official nginx image as the base image
FROM nginx:perl

# Copy the build output to replace the default nginx contents.
COPY --from=build /usr/local/app/dist/arosaje /usr/share/nginx/html
COPY ../docker/data-web/nginx.conf /etc/nginx/conf.d/default.conf


# Expose port 80
EXPOSE 80
