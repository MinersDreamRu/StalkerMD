package ru.minersdream.stalker.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.Quest;
import ru.minersdream.stalker.main.STALKERMain;

public class GuiPDA extends GuiScreen{
	private static final ResourceLocation BACKGROUND = new ResourceLocation(STALKERMain.MODID, "textures/gui/pda.png");
	private static final ResourceLocation MODULES = new ResourceLocation(STALKERMain.MODID, "textures/gui/pda_modules.png");
	private static final ResourceLocation QUEST_TAB = new ResourceLocation(STALKERMain.MODID, "textures/gui/pda_questtab.png");
	private static final ResourceLocation FACRTIONICONS = new ResourceLocation(STALKERMain.MODID, "textures/gui/groups.png");
	private static final ResourceLocation FACRTION_TAB = new ResourceLocation(STALKERMain.MODID, "textures/gui/pda_factionstab.png");
	
	private ArrayList<Faction> playerFactions;
	
	private int activeQuest=0;
	private int activeFaction=0;
    private int activePageId=-1;
    private GuiButtonPDA buttonMap;
    private GuiButtonPDA buttonQuests;
    private GuiButtonPDA buttonHealth;
    private GuiButtonPDA buttonRelationship;
    
    private GuiButtonPDA prevQuest;
    private GuiButtonPDA nextQuest;
    
    public static FontRenderer fontRend;
    
	public GuiPDA() {}
	
	@Override
	public void initGui() {
        fontRend=fontRenderer;
        
        int i = (this.width - 256) / 2;
        int j = height/2-100;
        
        this.buttonMap = this.addButton(new GuiButtonPDA(0, i+200-50, j+8, 0, 66, 50, 20, I18n.format("stalker.pda.ButtonMap")));
        this.buttonQuests = this.addButton(new GuiButtonPDA(1, i+200, j+8, 0, 66, 50, 20, I18n.format("stalker.pda.ButtonQuests")));
        //this.buttonHealth = this.addButton(new GuiButtonPDA(2, i+50*2+6, j+8, 0, 66, 50, 20, I18n.format("stalker.pda.ButtonHealth")));
        //this.buttonRelationship = this.addButton(new GuiButtonPDA(3, i+50*3+6, j+8, 0, 66, 50, 20, I18n.format("stalker.pda.Relationship")));
        
        this.prevQuest = this.addButton(new GuiButtonPDA(4, i+8, j+30, 0, 0, 30, 30, null));
        this.nextQuest = this.addButton(new GuiButtonPDA(5, i+218, j+30, 30, 0, 30, 30, null));
        if(activePageId==-1) {
        prevQuest.visible=false;
        nextQuest.visible=false;
        
        }
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
        int i = (this.width - 256) / 2;
        int j = height/2-100;
		if(button.id<4)
			activePageId=button.id;
		
		switch (button.id) {
		case 0:
			buttonMap.enabled=false;
			buttonQuests.enabled=true;
		   // buttonHealth.enabled=true;
		  //  buttonRelationship.enabled=true;
	        prevQuest.visible=false;
	        nextQuest.visible=false;
			break;
		case 1:
			buttonMap.enabled=true;
			buttonQuests.enabled=false;
		   // buttonHealth.enabled=true;
		   // buttonRelationship.enabled=true;
	        prevQuest.visible=true;
	        nextQuest.visible=true;
			break;
		case 2:
			buttonMap.enabled=true;
			buttonQuests.enabled=true;
		   // buttonHealth.enabled=false;
		    //buttonRelationship.enabled=true;
	        prevQuest.visible=false;
	        nextQuest.visible=false;
			break;
		case 3:
			buttonMap.enabled=true;
			buttonQuests.enabled=true;
		   // buttonHealth.enabled=true;
		   // buttonRelationship.enabled=false;
	        prevQuest.visible=true;
	        nextQuest.visible=true;
		
			break;
		case 4:
			switch (activePageId) {
			case 1:
				if(activeQuest>0)
					activeQuest--;
			break;

			default:
				
			break;
			}
			break;
		case 5:
			switch (activePageId) {
			case 1:
				if(activeQuest+1<PlayerQuestController.getActiveQuests(mc.player).size())
					activeQuest++;
				break;

			default:
				break;
			}
			
			break;

		default:
			break;
		}

		super.actionPerformed(button);
	}
	
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {        
        

		
    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    	int i = (this.width - 256) / 2;
    	int j = height/2-100;
		
    	
        
        this.mc.getTextureManager().bindTexture(BACKGROUND);
	    

        this.drawTexturedModalRect(i, j, 0, 0, 256, 188);
        

       
        switch (activePageId) {
		case 0:
			this.fontRenderer.drawString(TextFormatting.RED+(TextFormatting.BOLD+I18n.format("stalker.pda.nosignal")), i+100, j+90, 1);
			
			
			break;
		case 1:
	        this.mc.getTextureManager().bindTexture(QUEST_TAB);
	        this.drawTexturedModalRect(i, j+29, 0, 0, 256, 149);
	        Vector<Quest> quests=PlayerQuestController.getActiveQuests(mc.player);
	        int questCount=PlayerQuestController.getActiveQuests(mc.player).size();
			if (questCount>0) {
				String questName=quests.get(activeQuest).getName();
				String questCategory=quests.get(activeQuest).getCategory().getName();
				String questDiscription=quests.get(activeQuest).getLogText();
				String[] text = questDiscription.split(" ");
				String[] questLog= {"","","","","","",""};
				this.fontRenderer.drawString(TextFormatting.WHITE+questCategory, i+45, j+35, 1);
				this.fontRenderer.drawString(TextFormatting.WHITE+questName, i+45, j+50, 1);
				int k=0;
				for (int k2= 0; k2 < text.length; k2++) {
					if(questLog[k].length()+1+text[k2].length()<58) {
						questLog[k]+=text[k2]+" ";
					}
					else {
						k++;
						k2--;
					}	
				}
				for (int k2 = 0; k2 < questLog.length; k2++) {
					this.fontRenderer.drawString(TextFormatting.WHITE+questLog[k2], i+15, j+65+k2*9, 1);
					
				}
				switch (quests.get(activeQuest).getType()) {
				default:
					this.fontRenderer.drawString(TextFormatting.RED+I18n.format("stalker.pda.questType.0"), i+15, j+132, 1);
					drawQuestObjectives(quests, i, j);
					break;
				case 1:
					this.fontRenderer.drawString(TextFormatting.RED+I18n.format("stalker.pda.questType.1"), i+15, j+132, 1);
					drawQuestObjectives(quests, i, j);
					break;
				case 2:
					this.fontRenderer.drawString(TextFormatting.RED+I18n.format("stalker.pda.questType.2"), i+15, j+132, 1);
					drawQuestObjectives(quests, i, j);
					break;
				case 3:
					this.fontRenderer.drawString(TextFormatting.RED+I18n.format("stalker.pda.questType.3"), i+15, j+132, 1);
					drawQuestObjectives(quests, i, j);
					break;
				case 4:
					this.fontRenderer.drawString(TextFormatting.RED+I18n.format("stalker.pda.questType.4"), i+15, j+132, 1);
					drawQuestObjectives(quests, i, j);
					break;
				case 5:
					this.fontRenderer.drawString(TextFormatting.RED+I18n.format("stalker.pda.questType.5"), i+15, j+132, 1);
					drawQuestObjectives(quests, i, j);
					break;

				}
				
			}else {
				this.fontRenderer.drawString(TextFormatting.RED+I18n.format("stalker.pda.missingQuests"), i+90, j+35, 1);
			}
			
			
			
			
			
			
			
			break;
		case 2:
			this.mc.getTextureManager().bindTexture(MODULES);
			//long radiationPlayer = mc.player.getEntityData().getLong("radiation");
			//long psiradiationPlayer = mc.player.getEntityData().getLong("psiradiation");
	        //Радиация
	        //this.drawTexturedModalRect(baseX+7, baseY+35, 0, 218, 244, 38);
	        //this.drawTexturedModalRect(baseX+7, baseY+35, 0, 150, 244, 15);
	        this.drawTexturedModalRect(i+7, j+30, 0, 196, 244, 30);
	        //this.drawTexturedModalRect(baseX+7, baseY, 0, 150, 244, 15);

			//Пси-Здоровье
			//this.drawTexturedModalRect(xCoord, yCoord, minU, minV, maxU, maxV);
	        this.drawTexturedModalRect(i+7, j+60, 0, 226, 244, 30);
			
			
			
			
			
			break;
		case 3:
			this.mc.getTextureManager().bindTexture(FACRTION_TAB);
			
			
			
			
			
			
			
			
			
			break;

		default:
			break;
		}
        
        //this.fontRenderer.drawString(TextFormatting.WHITE+(TextFormatting.BOLD+getTime()), i+10, j+15, 1);
        //new GuiButton(buttonId, x, y, widthIn, heightIn, buttonText)
        /*
        if (this.bookGettingSigned)
        {
            String s = this.bookTitle;

            if (this.bookIsUnsigned)
            {
                if (this.updateCount / 6 % 2 == 0)
                {
                    s = s + "" + TextFormatting.BLACK + "_";
                }
                else
                {
                    s = s + "" + TextFormatting.GRAY + "_";
                }
            }

            String s1 = I18n.format("book.editTitle");
            int k = this.fontRenderer.getStringWidth(s1);
            this.fontRenderer.drawString(s1, i + 36 + (116 - k) / 2, 34, 0);
            int l = this.fontRenderer.getStringWidth(s);
            this.fontRenderer.drawString(s, i + 36 + (116 - l) / 2, 50, 0);
            String s2 = I18n.format("book.byAuthor", this.editingPlayer.getName());
            int i1 = this.fontRenderer.getStringWidth(s2);
            this.fontRenderer.drawString(TextFormatting.DARK_GRAY + s2, i + 36 + (116 - i1) / 2, 60, 0);
            String s3 = I18n.format("book.finalizeWarning");
            this.fontRenderer.drawSplitString(s3, i + 36, 82, 116, 0);
        }
        else
        {
            String s4 = I18n.format("book.pageIndicator", this.currPage + 1, this.bookTotalPages);
            String s5 = "";

            if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount())
            {
                s5 = this.bookPages.getStringTagAt(this.currPage);
            }

            if (this.bookIsUnsigned)
            {
                if (this.fontRenderer.getBidiFlag())
                {
                    s5 = s5 + "_";
                }
                else if (this.updateCount / 6 % 2 == 0)
                {
                    s5 = s5 + "" + TextFormatting.BLACK + "_";
                }
                else
                {
                    s5 = s5 + "" + TextFormatting.GRAY + "_";
                }
            }
            else if (this.cachedPage != this.currPage)
            {
                if (ItemWrittenBook.validBookTagContents(this.book.getTagCompound()))
                {
                    try
                    {
                        ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(s5);
                        this.cachedComponents = itextcomponent != null ? GuiUtilRenderComponents.splitText(itextcomponent, 116, this.fontRenderer, true, true) : null;
                    }
                    catch (JsonParseException var13)
                    {
                        this.cachedComponents = null;
                    }
                }
                else
                {
                    TextComponentString textcomponentstring = new TextComponentString(TextFormatting.DARK_RED + "* Invalid book tag *");
                    this.cachedComponents = Lists.newArrayList(textcomponentstring);
                }

                this.cachedPage = this.currPage;
            }

            int j1 = this.fontRenderer.getStringWidth(s4);
            this.fontRenderer.drawString(s4, i - j1 + 192 - 44, 18, 0);

            if (this.cachedComponents == null)
            {
                this.fontRenderer.drawSplitString(s5, i + 36, 34, 116, 0);
            }
            else
            {
                int k1 = Math.min(128 / this.fontRenderer.FONT_HEIGHT, this.cachedComponents.size());

                for (int l1 = 0; l1 < k1; ++l1)
                {
                    ITextComponent itextcomponent2 = this.cachedComponents.get(l1);
                    this.fontRenderer.drawString(itextcomponent2.getUnformattedText(), i + 36, 34 + l1 * this.fontRenderer.FONT_HEIGHT, 0);
                }

                ITextComponent itextcomponent1 = this.getClickedComponentAt(mouseX, mouseY);

                if (itextcomponent1 != null)
                {
                    this.handleComponentHover(itextcomponent1, mouseX, mouseY);
                }
            }
        }
         */
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    private String getTime() {
    	String time="";
    	long worldTime = mc.world.getWorldTime();
    	time+=worldTime%1000;
    	return time;
    }
    private void drawQuestObjectives(Vector<Quest> quests, int i, int j) {
    	int yoffset = 0;
    	for (final IQuestObjective objective : quests.get(activeQuest).questInterface.getObjectives(mc.player)) {
            this.mc.fontRenderer.drawString(TextFormatting.WHITE+"- " + objective.getText(), i+100, j+132+yoffset, 1);
            yoffset+=10;
        }
    }
	
	
}
