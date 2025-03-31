package com.seriousmastering;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiMessageReceivedCallback;
import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.*;

public class XTouchMiniExtension extends ControllerExtension
{
   protected XTouchMiniExtension(final XTouchMiniExtensionDefinition definition, final ControllerHost host)
   {
      super(definition, host);
   }

   private Application mApplication;

   private Arranger mArranger;

   private MidiIn midiIn;

   private MidiOut midiOut;

   private Transport mTransport;

   private PinnableCursorDevice mCursorDevice;

   private CursorRemoteControlsPage mRemoteControls;

   @Override
   public void init()
   {
      final ControllerHost host = getHost();
      midiIn = host.getMidiInPort(0);
      midiIn.setMidiCallback((ShortMidiMessageReceivedCallback)msg -> onMidi(msg));
      midiOut = host.getMidiOutPort(0);

      mApplication = host.createApplication();
      mArranger = host.createArranger();
      mTransport = host.createTransport();

      // make sure you can check these attributes
      mTransport.isArrangerLoopEnabled().markInterested();
      mTransport.isPlaying().markInterested();
      mTransport.isArrangerRecordEnabled().markInterested();

      CursorTrack mCursorTrack = host.createCursorTrack("0", "X Touch Mini", 0, 0, true);
      mCursorDevice = mCursorTrack.createCursorDevice();
      mRemoteControls = mCursorDevice.createCursorRemoteControlsPage(8);

      for (int i = 0; i < 8; ++i)
      {
         final RemoteControl parameter = mRemoteControls.getParameter(i);
         parameter.setIndication(true);
         parameter.setLabel("Encoder " + (i + 1));
      }

      host.showPopupNotification("X-Touch mini Initialized");
   }

   @Override
   public void exit()
   {
      //Nothing
   }

   @Override
   public void flush()
   {
      //Nothing
   }

   private void onMidi(ShortMidiMessage msg)
   {
      if (msg.isControlChange())
      {
         handleControlChange(msg);
      }
      else if (msg.isNoteOn() || msg.isNoteOff())
      {
         handleNote(msg);
      }
   }

   private void handleControlChange(ShortMidiMessage msg)
   {
      int cc = msg.getData1();
      int value = msg.getData2();
      handleFader(cc, value);
      handleEncoders(cc, value);
      handleButtons(cc, value);
   }

   private void handleNote(ShortMidiMessage msg)
   {
      int cc = msg.getData1();
      int value = msg.getData2();
      //TODO handle notes
   }

   private void handleFader(int cc, int value)
   {
      if (cc == 0 || cc == 25)
      {
         mCursorDevice.channel().volume().set(value, 128);
      }
   }

   private void handleEncoders(int cc, int value)
   {
      switch (cc)
      {
      case 1:
      case 26:
         mRemoteControls.getParameter(0).set(value, 128);
         break;
      case 2:
      case 27:
         mRemoteControls.getParameter(1).set(value, 128);
         break;
      case 3:
      case 28:
         mRemoteControls.getParameter(2).set(value, 128);
         break;
      case 4:
      case 29:
         mRemoteControls.getParameter(3).set(value, 128);
         break;
      case 5:
      case 30:
         mRemoteControls.getParameter(4).set(value, 128);
         break;
      case 6:
      case 31:
         mRemoteControls.getParameter(5).set(value, 128);
         break;
      case 7:
      case 32:
         mRemoteControls.getParameter(6).set(value, 128);
         break;
      case 8:
      case 33:
         mRemoteControls.getParameter(7).set(value, 128);
         break;
      default:
         break;
      }
   }

   private void handleButtons(int cc, int value)
   {
      if (value != 0)
      {
         switch (cc)
         {
         case 9:
         case 34:
            mApplication.selectFirst();
            break;
         case 10:
         case 35:
            mApplication.selectPrevious();
            break;
         case 11:
         case 36:
            mApplication.selectNext();
            break;
         case 12:
         case 37:
            mApplication.selectLast();
            break;
         case 13:
         case 38:
            mArranger.zoomToFit();
            break;
         case 14:
         case 39:
            mArranger.zoomToSelection();
            break;
         case 15:
         case 40:
            mApplication.zoomIn();
            break;
         case 16:
         case 41:
            mApplication.zoomOut();
            break;
         case 17:
         case 42:
            mApplication.undo();
            break;
         case 18:
         case 43:
            mApplication.redo();
            break;
         case 19:
         case 44:
            mTransport.rewind();
            break;
         case 20:
         case 45:
            mTransport.fastForward();
            break;
         case 21:
         case 46:
            mTransport.isArrangerLoopEnabled().toggle();
            sendButtonState(midiOut, Button.BUTTON_13, !mTransport.isArrangerLoopEnabled().get());
            break;
         case 22:
         case 47:
            mTransport.stop();
            if (mTransport.isArrangerRecordEnabled().get())
            {
               mTransport.isArrangerRecordEnabled().set(false);
               sendButtonState(midiOut, Button.BUTTON_15, false);
            }
            break;
         case 23:
         case 48:
            mTransport.isPlaying().toggle();
            sendButtonState(midiOut, Button.BUTTON_15, !mTransport.isPlaying().get());
            break;
         case 24:
         case 49:
            mTransport.isArrangerRecordEnabled().toggle();
            mTransport.isPlaying().set(!mTransport.isArrangerRecordEnabled().get());
            sendButtonState(midiOut, Button.BUTTON_15, !mTransport.isPlaying().get());
            sendButtonState(midiOut, Button.BUTTON_16, !mTransport.isArrangerRecordEnabled().get());
            break;
         }
      }
   }

   /*
    Doesn't seem to work as Behringer documentation is providing.
    */
   private static void sendButtonState(MidiOut midiOut, Button note, boolean on)
   {
      midiOut.sendMidi(note(on), note.getValue(), led(on));
   }

   private static int note(boolean on)
   {
      if (on)
      {
         return ShortMidiMessage.NOTE_ON;
      }
      return ShortMidiMessage.NOTE_OFF;
   }

   private static int led(boolean on)
   {
      if (on)
      {
         return LedState.ON.getValue();
      }
      return LedState.OFF.getValue();
   }

   private enum LedState
   {
      OFF(0), ON(1), BLINKING(2);

      private final int value;

      LedState(int value)
      {

         this.value = value;
      }

      public int getValue()
      {
         return value;
      }
   }

   private enum Button
   {
      BUTTON_1(0), BUTTON_2(1), BUTTON_3(2), BUTTON_4(3), BUTTON_5(4), BUTTON_6(5), BUTTON_7(6), BUTTON_8(
      7), BUTTON_9(8), BUTTON_10(9), BUTTON_11(10), BUTTON_12(11), BUTTON_13(12), BUTTON_14(13), BUTTON_15(
      14), BUTTON_16(15);

      private final int value;

      Button(int value)
      {
         this.value = value;
      }

      public int getValue()
      {
         return value;
      }
   }
}