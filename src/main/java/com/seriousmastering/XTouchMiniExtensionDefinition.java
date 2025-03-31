package com.seriousmastering;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

import java.util.UUID;

public class XTouchMiniExtensionDefinition extends ControllerExtensionDefinition
{
   private static final UUID DRIVER_ID = UUID.fromString("b9960406-709e-45ce-9925-db8ef624504b");

   @Override
   public String getName()
   {
      return "X-Touch mini";
   }

   @Override
   public String getAuthor()
   {
      return "Serious Mastering";
   }

   @Override
   public String getVersion()
   {
      return "1.0.0";
   }

   @Override
   public UUID getId()
   {
      return DRIVER_ID;
   }

   @Override
   public String getHardwareVendor()
   {
      return "Behringer";
   }

   @Override
   public String getHardwareModel()
   {
      return "X-Touch mini";
   }

   @Override
   public String getHelpFilePath()
   {
      return "https://www.behringer.com/product.html?modelCode=0808-AAF";
   }

   @Override
   public int getRequiredAPIVersion()
   {
      return 20;
   }

   @Override
   public int getNumMidiInPorts()
   {
      return 1;
   }

   @Override
   public int getNumMidiOutPorts()
   {
      return 1;
   }

   @Override
   public void listAutoDetectionMidiPortNames(
      final AutoDetectionMidiPortNamesList list,
      final PlatformType platformType)
   {
      if (platformType == PlatformType.WINDOWS)
      {
         list.add(new String[] { "X-TOUCH MINI" }, new String[] { "X-TOUCH MINI" });
      }
      else if (platformType == PlatformType.MAC)
      {
         // not sure
         list.add(new String[] { "X-TOUCH MINI" }, new String[] { "X-TOUCH MINI\n" });
      }
      else if (platformType == PlatformType.LINUX)
      {
         // not sure
         list.add(new String[] { "X-TOUCH MINI" }, new String[] { "X-TOUCH MINI\n" });
      }
   }

   @Override
   public XTouchMiniExtension createInstance(final ControllerHost host)
   {
      return new XTouchMiniExtension(this, host);
   }
}
