import pygame
from pygame.locals import *
import math
import random



# 2 - Initialize the game
pygame.init()
width, height = 640, 480
screen=pygame.display.set_mode((width, height))
keys =[False,False,False,False] 
playerpos=[100,100]
bullets=[]
badguys=[[640,100]]
healthvalue=194
score = 0
exitcode = 0
LEVEL_PAR_SCORE = 40
SPEED_PAR_SCORE = 10
TIMEOUT = 120000
pygame.mixer.init()

class returnCode:
    
    def __init__(self,score,exitCode):
        self.score = score
        self.exitCode = exitCode
        
# load images
try :
    player = pygame.image.load("resources/images/tank.png")
    grass = pygame.image.load("resources/images/grass.png")
    castle = pygame.image.load("resources/images/castle.png")
    castle1=pygame.image.load("resources/images/castle23.png")
    arrow = pygame.image.load("resources/images/bullet.png")
    badguyimg=pygame.image.load("resources/images/badguy.png")
    healthbar = pygame.image.load("resources/images/healthbar.png")
    health = pygame.image.load("resources/images/health.png")
    gameover = pygame.image.load("resources/images/gameover.png")
    levelchange= pygame.image.load("resources/images/levelimg.png")
    youwin = pygame.image.load("resources/images/youwin.png")
except Exception as e:
    print e
    exit()


#load audio
try :
    hit = pygame.mixer.Sound("resources/audio/explode.wav")
    enemy = pygame.mixer.Sound("resources/audio/enemy.wav")
    shoot = pygame.mixer.Sound("resources/audio/shoot.wav")
    pygame.mixer.music.load('resources/audio/gamestart.wav')
except Exception as e:
    print e
    exit()
hit.set_volume(0.05)
enemy.set_volume(0.05)
shoot.set_volume(0.05)    
pygame.mixer.music.play(-1, 0.0)
pygame.mixer.music.set_volume(0.25)

def addEvent(playerpos1):
    for event in pygame.event.get():
        # check if the event is the X button
        
        if event.type==pygame.QUIT:
            # if it is quit the game
            pygame.quit()
            exit(0)
        if event.type == pygame.KEYDOWN:
            if event.key==K_UP:
                keys[0]=True
            elif event.key==K_LEFT:
                keys[1]=True
            elif event.key==K_DOWN:
                keys[2]=True
            elif event.key==K_RIGHT:
                keys[3]=True
        if event.type == pygame.KEYUP:
            if event.key==pygame.K_UP:
                keys[0]=False
            elif event.key==pygame.K_LEFT:
                keys[1]=False
            elif event.key==pygame.K_DOWN:
                keys[2]=False
            elif event.key==pygame.K_RIGHT:
                keys[3]=False
        if event.type==pygame.MOUSEBUTTONDOWN or (event.type==pygame.KEYDOWN and event.key==pygame.K_SPACE):
            shoot.play()
            position=pygame.mouse.get_pos()
            bullets.append([math.atan2(position[1]-(playerpos1[1]+32),position[0]-(playerpos1[0]+26)),playerpos1[0]+32,playerpos1[1]+32])
               
    return keys          

def keyPos(keys,playerpos):
    if keys[0]:
        playerpos[1]-=5
    elif keys[2]:
        playerpos[1]+=5
    if keys[1]:
        playerpos[0]-=5
    elif keys[3]:
        playerpos[0]+=5

def setPlayerPos(playerpos):
    position = pygame.mouse.get_pos()
    angle = math.atan2(position[1]-(playerpos[1]+32),position[0]-(playerpos[0]+26))
    playerrot = pygame.transform.rotate(player, 360-angle*57.29)
    playerpos1 = (playerpos[0]-playerrot.get_rect().width/2, playerpos[1]-playerrot.get_rect().height/2)
    screen.blit(playerrot, playerpos1)
    return playerpos1
            
def setGrassCastle():
    for x in range(width/grass.get_width()+1):
        for y in range(height/grass.get_height()+1):
            screen.blit(grass,(x*100,y*100))
    screen.blit(castle,(0,30))
    screen.blit(castle1,(0,240))
    

def drawbullet():
    for bullet in bullets:
        index=0
        velx=math.cos(bullet[0])*10
        vely=math.sin(bullet[0])*10
        bullet[1]+=velx
        bullet[2]+=vely
        if bullet[1]<-64 or bullet[1]>640 or bullet[2]<-64 or bullet[2]>480:
            bullets.pop(index)
        index+=1
    for projectile in bullets:
            arrow1 = pygame.transform.rotate(arrow, 360-projectile[0]*57.29)
            screen.blit(arrow1, (projectile[1], projectile[2]))    
    
def ifClicked():
    while True:
        for event in pygame.event.get():
            if (event.type == pygame.MOUSEBUTTONDOWN):
                return;

def cleanUpScreen(badguys, bullet):
    del badguys[:]
    del bullet[:]
    

def maingame(playerpos,healthvalue,initScore,speed):
    score = initScore
    while True:
        #clear the screen before drawing it again
        screen.fill(0)
        #set grass and Castle
        setGrassCastle()
        #draw the screen elements
        playerpos1= setPlayerPos(playerpos)

        #draw bullets
        drawbullet()

        #draw badguys, add more bad guys in the list of badguys
        tempScore = score;
        while tempScore >= (initScore + SPEED_PAR_SCORE * len(badguys)):
            badguys.append([640,random.randint(50,430)])
            tempScore -= SPEED_PAR_SCORE           
        index=0
        for badguy in badguys:
            if badguy[0]<-64:
                badguys.pop(index)
                hit.play()
            badguy[0]-=speed
            badrect=pygame.Rect(badguyimg.get_rect())
            badrect.top=badguy[1]
            badrect.left=badguy[0]
            if badrect.left<64:
                healthvalue -= random.randint(5,20)
                badguys.remove(badguy)
            index1=0
            for bullet in bullets:
                bullrect=pygame.Rect(arrow.get_rect())
                bullrect.left=bullet[1]
                bullrect.top=bullet[2]
                if badrect.colliderect(bullrect):
                    enemy.play()
                    if badguy in badguys:
                        badguys.remove(badguy)
                    bullets.pop(index1)
                    score += 5
                index1+=1
            #6.3.3 - Next bad guy 
            index+=1
        for badguy in badguys:
            screen.blit(badguyimg, badguy)
        font = pygame.font.Font(None, 24)
        text = font.render(str((90000-pygame.time.get_ticks())/60000)+":"+str((90000-pygame.time.get_ticks())/1000%60).zfill(2), True, (0,0,0))
        textRect = text.get_rect()
        textRect.topright=[635,5]
        screen.blit(text, textRect)
        
        #Draw health Bar
        screen.blit(healthbar, (5,5))
        for health1 in range(healthvalue):
            screen.blit(health, (health1+8,8))
            
        #Draw Score
        scoreFont = pygame.font.Font(None, 24)
        scoreStr= font.render("score: "+str(score),True,(0,0,0))
        scoretext = scoreStr.get_rect()
        scoretext.topright =[635,50]
        screen.blit(scoreStr, scoretext)
        #update the screen
        pygame.display.flip()
        
        keyPos(addEvent(playerpos1),playerpos)
        if score >= initScore + LEVEL_PAR_SCORE:
            cleanUpScreen(badguys, bullets)
            retCode = returnCode(score,2)
            return retCode
        if pygame.time.get_ticks() >= TIMEOUT:
            cleanUpScreen(badguys, bullets)
            retCode = returnCode(score,1)
            return retCode
        if healthvalue<=0:
            cleanUpScreen(badguys, bullets)
            retCode = returnCode(score,0)
            return retCode

speed = 5
while(True):
    if (exitcode == 0 or exitcode == 2):
        retcode = maingame(playerpos,healthvalue,score,speed)
    exitcode = retcode.exitCode
    score = retcode.score
    speed += 3
    if exitcode == 0:
        pygame.font.init()
        font = pygame.font.Font(None, 24)
        text = font.render("your Score is"+str(score),True, (0,255,0))
        textRect = text.get_rect()
        textRect.centerx = screen.get_rect().centerx
        textRect.centery = screen.get_rect().centery+50
        screen.blit(gameover, (0,0))
        screen.blit(text, textRect)
        
        break;
 
    elif exitcode ==2:
        pygame.font.init()
        font = pygame.font.Font(None, 24)
        text = font.render("CLICK MOUSE FOR LEVEL UP",True, (0,255,0))
        textRect = text.get_rect()
        textRect.centerx = screen.get_rect().centerx
        textRect.centery = screen.get_rect().centery+24
        screen.blit(levelchange, (0,0))
        screen.blit(text, textRect)
        pygame.display.flip()
        pygame.mixer.music.pause()
        ifClicked()
        pygame.mixer.music.unpause()
             
    else :
        pygame.font.init()
        font = pygame.font.Font(None, 24)
        text = font.render("your Score is"+str(score),True, (0,255,0))
        textRect = text.get_rect()
        textRect.centerx = screen.get_rect().centerx
        textRect.centery = screen.get_rect().centery+50
        screen.blit(youwin, (0,0))
        screen.blit(text, textRect)
        break;
        
while 1:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            exit(0)
    pygame.display.flip()



