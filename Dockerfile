# Use the existing base image
FROM henrotaym/openjdk:23

# Set non-interactive mode to avoid prompts
ENV DEBIAN_FRONTEND=noninteractive

# Install locales and generate en_US.UTF-8
RUN apt-get update && apt-get install -y locales && \
    locale-gen en_US.UTF-8 && \
    update-locale LANG=en_US.UTF-8 LC_ALL=en_US.UTF-8

# Set UTF-8 as the default environment variables
ENV LANG=en_US.UTF-8
ENV LC_ALL=en_US.UTF-8

# Copy project files into the container
COPY . .