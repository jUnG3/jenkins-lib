package de.jenkins.util

interface DockerRepository<T> {
    T fetchItems();
}