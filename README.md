[![Build Status](https://travis-ci.com/Madave94/sple-exercise.svg?token=sBsi8fZsf8GM9ptYZkau&branch=master)](https://travis-ci.com/Madave94/sple-exercise)
# sple-exercise
Software Product Line Engineering Samples to work on

by David Tschirschwitz, Digital Engineering, 119383

## How to use the plugin and plugin loader:

Start chat server with

    java plugin.StartServer
    
and start a chat client with

    java plugin.StartClient

The Plugin Loader is composed by the following classes (all in package plugin):

PluginConfig - use this file to change the used Configuration.
Three classes inherit from this class:
- StartServer
- StartClient
- Plugin

## Unused classes / God interface plugin

This part is my secondary implementation, which appears to be wrong, you still can check it if you like maybe as some kind of 'antipattern' example. Consisting out of ConfigSubstitutePlugin & SubstitutePlugin.

Unused: ClientPlugin
