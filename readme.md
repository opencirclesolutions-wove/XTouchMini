# Simple Bitwig Extension for Behringer X-Touch mini

This extension is made for Bitwig API version 20 and later.\
That would be about Bitwig 5.3.x and up. It's only tested on Windows 11.\
This extension uses hardcoded values and only works with the proper configuration of the Behringer X-Touch mini.\
Behringer X-Touch mini was tested with firmware 1.21.\
Mainly because making it configurable in Bitwig self is a lot of work.\
Maybe on a later date I can do something about that.

## Behringer X-Touch mini Configuration
Download and use the Behringer X-Touch mini editor.\
The default settings for the X-Touch mini are not usable for Bitwig.\
X-Touch mini has many options for configuration and which means that on the Bitwig side you could have many options.\
Hence the choice to have a fixed configuration for the Behringer X-Touch mini.\
Use LayerA.bin and/or LayerB.bin to load the configuration for the X-Touch mini.\

## Bitwig Configuration

This configuration is hardcoded\
By default midi channel 1 is used.

### Layer A

Fader
- CC00 is mapped to the fader for the selected channel (cursor)

Encoders
- CC01 is mapped to encoder 1 and controls generic control 1
- CC02 is mapped to encoder 2 and controls generic control 2
- CC03 is mapped to encoder 3 and controls generic control 3
- CC04 is mapped to encoder 4 and controls generic control 4
- CC05 is mapped to encoder 5 and controls generic control 5
- CC06 is mapped to encoder 6 and controls generic control 6
- CC07 is mapped to encoder 7 and controls generic control 7
- CC08 is mapped to encoder 8 and controls generic control 8

Top row buttons
- CC09 is mapped to button 1 and controls select first
- CC10 is mapped to button 2 and controls select previous
- CC11 is mapped to button 3 and controls select next
- CC12 is mapped to button 4 and controls select last
- CC13 is mapped to button 5 and controls select zoom to fit
- CC14 is mapped to button 6 and controls select zoom to selection
- CC15 is mapped to button 7 and controls select zoom in
- CC16 is mapped to button 8 and controls select zoom out

Bottom row buttons
- CC17 is mapped to button 9 and controls undo
- CC18 is mapped to button 10 and controls redo
- CC19 is mapped to button 11 and controls backward
- CC20 is mapped to button 12 and controls forward
- CC21 is mapped to button 13 and controls loop
- CC22 is mapped to button 14 and controls stop
- CC23 is mapped to button 15 and controls play
- CC24 is mapped to button 16 and controls record

Encoder push buttons are mapped to notes C-2 ... G-2 but aren't used.\

### Layer B

Fader
- CC25 is mapped to the fader for the selected channel (cursor)

Encoders
- CC26 is mapped to encoder 1 and controls generic control 1
- CC27 is mapped to encoder 2 and controls generic control 2
- CC28 is mapped to encoder 3 and controls generic control 3
- CC29 is mapped to encoder 4 and controls generic control 4
- CC30 is mapped to encoder 5 and controls generic control 5
- CC31 is mapped to encoder 6 and controls generic control 6
- CC32 is mapped to encoder 7 and controls generic control 7
- CC33 is mapped to encoder 8 and controls generic control 8

Top row buttons
- CC34 is mapped to button 1 and controls select first
- CC35 is mapped to button 2 and controls select previous
- CC36 is mapped to button 3 and controls select next
- CC37 is mapped to button 4 and controls select last
- CC38 is mapped to button 5 and controls select zoom to fit
- CC39 is mapped to button 6 and controls select zoom to selection
- CC40 is mapped to button 7 and controls select zoom in
- CC41 is mapped to button 8 and controls select zoom out

Bottom row buttons
- CC42 is mapped to button 9 and controls undo
- CC43 is mapped to button 10 and controls redo
- CC44 is mapped to button 11 and controls backward
- CC45 is mapped to button 12 and controls forward
- CC46 is mapped to button 13 and controls loop
- CC47 is mapped to button 14 and controls stop
- CC48 is mapped to button 15 and controls play
- CC49 is mapped to button 16 and controls record

Encoder push buttons are mapped to notes C-1 ... G-1 but aren't used.\

## Versions

1.0.0 Initial release

### Known issues:
- Leds on buttons of Behringer X-Touch mini will not light up.
