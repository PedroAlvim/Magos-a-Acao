/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package magosminegame;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author PedroAlvim
 */

public class InterfaceJogo extends javax.swing.JFrame {
    int dado, manaAtual = 10, manaJog, ContH = 1, vidaJ = 20, skill = 0, vidaM = 45;
    String acao, h;
   
    public InterfaceJogo() {
        initComponents();
        EnableDado(0);
        DisebleSkill();
        EnableMana (0);
        Historia(ContH);
     }  
        
    //--------MECANICAS-----------//

   int Rolard20(){ //Rolar dado de 20 fases//
        int i;
        Random random = new Random();
        i = random.nextInt(1, 21);
        return(i);
    }
   
   int Rolard4(){ //Rolar dado de 4 fases//
        int i;
        Random random = new Random();
        i = random.nextInt(1, 5);
        return(i);
    }
   
   int Rolard10(){ //Rolar dado de 10 fases//
        int i;
        Random random = new Random();
        i = random.nextInt(1, 11);
        return(i);
    }
   
    int Rolard6(){ //Rolar dado de 6 fases//
        int i;
        Random random = new Random();
        i = random.nextInt(1, 7);
        return(i);
    }
   
     void EnableDado(int v){ //Ligar botão de rolar dado//
       if(v == 1){
           btn_Roll.setEnabled(true);
       }else{
           btn_Roll.setEnabled(false);
       }
    }
     
    void Ataque(int v){
     //mana necessaria para as skill: Punho de fogo 0, Cura mitica 1, 
     //Nevasca 2 e Buraco negro 4
        DisebleSkill();
        EnableMana(0);
        int i = 0, dano = 0;
        
        switch (v) {
            case 1 -> { //punho de fogo//
                ta_Campo.setText(h + "\nPunho de Fogo");
                h = ta_Campo.getText();
                
                i = Rolard20();
                if(i > 10){
                   dano = Rolard4();
                   DanoTroll(v, dano);
                }else{
                    ContH = 4;
                    Historia(ContH);
                }             
            }
            case 2 -> { //cura mitica//
                manaAtual = manaAtual - 1;
                dano = Rolard6();
                ta_Campo.setText(h + "\n\nCura Mitica\n\nVocê canaliza sua energia e acalma seu corpo, você se curou em: " + dano + " pontos\n");
                h = ta_Campo.getText();
                vidaJ = vidaJ + dano;
                conferirV();
                ContH = 5;
                Historia(ContH);
                EnableMana(0);
            }
            case 3 -> { //lança de gelo//
                manaAtual = manaAtual - 2;
                ta_Campo.setText(h + "\n\nLança de Gelo");
                h = ta_Campo.getText();
                i = Rolard20();
                if(i > 10){
                   dano = Rolard10();
                   DanoTroll(v, dano);
                }else{
                    ContH = 4;
                    Historia(ContH);
                }
                EnableMana(0);
            }
            case 4 -> { //buraco negro//
                manaAtual = manaAtual - 4;
                ta_Campo.setText(h + "\n\nBuraco Negro");
                h = ta_Campo.getText();
                i = Rolard20();
                if(i > 10){
                   dano = Rolard10() + Rolard10() + Rolard4() + Rolard4();
                   DanoTroll(v, dano);
                }else{
                    ContH = 4;
                    Historia(ContH);
                }
                EnableMana(0);
            }
        }
    }
    
    void DanoTroll(int atk, int dano){
        switch (atk) {
            case 1 -> { //punho de fogo//
                ta_Campo.setText(h + "\n\nVocê concentra sua energia magica em seu punho, e "
                 + "sua mão começa a pegar fogo, então você acerta o peito da criatura com \num poderoso"
                + " soco de fogo\nVoce causou: " + dano + " de dano\n");
                h = ta_Campo.getText();
                vidaM = vidaM - dano;
                if(vidaM > 0){
                ContH = 5;
                Historia(ContH);
                }else{
                    morteTroll(atk);   
                }    
            }
            case 3 -> { //lança de gelo//
                ta_Campo.setText(h + "\n\nVocê começa a respirar mais fundo abaixando a temperatura ao redor, com um esticar de mão "
                        + "uma lança de gelo surge \ne então é atirada na criatura, acertando sua barriga!\nVoce causou: " + dano + " de dano\n");
                h = ta_Campo.getText();
                vidaM = vidaM - dano;
                if(vidaM > 0){
                ContH = 5;
                Historia(ContH);
                }else{
                    morteTroll(atk);   
                } 
            }
            case 4 -> { //buraco negro//
                ta_Campo.setText(h + "\n\nVocê estica o braço na direção da fera, então começa a canalizar seu ataque mais forte, e "
                        + "na palma de sua mão surge um buraco negro\nque suga o troll, causando terriveis danos e depois se dissipa!\nVoce causou: " + dano + " de dano\n");
                h = ta_Campo.getText();
                vidaM = vidaM - dano;
                if(vidaM > 0){
                ContH = 5;
                Historia(ContH);
                }else{
                    morteTroll(atk);   
                }
                
            }
        }
    }
    
    void morteTroll(int atk){
        EnableAcao(0);
        EnableMana(0);
        DisebleSkill();
        EnableDado(0);
        JFrame jFrame = new JFrame();
        
        switch (atk) {
            case 1 -> { //punho de fogo//
                JOptionPane.showMessageDialog(jFrame, "Com um poderoso soco de foco, o troll começa a pegar fogo assim queimando até a morte!\nVoce venceu! obrigado por jogar!");
            }
            case 3 -> { //lança de gelo//
                JOptionPane.showMessageDialog(jFrame, "Uma imensa lança de gelo é gerada e jogada no troll acertando o coração da fera que congela até a morte!\nVoce venceu! obrigado por jogar!");
            }
            case 4 -> { //buraco negro//
                JOptionPane.showMessageDialog(jFrame, "O buraco negro gerado em sua mão suga o troll e então o extingue para sempre!\nVoce venceu! obrigado por jogar!!");
            }
        }
        Menu i = new Menu();
        i.setVisible(true);
        dispose();
    }
     
    void EnableSkill(int v){ //ligar botões de habilidade//
        //mana necessaria para as skill: Punho de fogo 0, Cura mitica 1, 
        //Nevasca 2 e Buraco negro 4
        DisebleSkill();
        
        if(v <= 0){
            btn_skill1.setEnabled(true);
        }else if(v <= 1){
            btn_skill1.setEnabled(true);
            btn_skill2.setEnabled(true);
        }else if(v <= 2 || v <= 3){
            btn_skill1.setEnabled(true);
            btn_skill2.setEnabled(true);
            btn_skill3.setEnabled(true);
        }else if(v >= 4){
            btn_skill1.setEnabled(true);
            btn_skill2.setEnabled(true);
            btn_skill3.setEnabled(true);
            btn_skill4.setEnabled(true);
        }
        
    }
    
    void DisebleSkill(){ //desligar habilidade//
          btn_skill1.setEnabled(false);
          btn_skill2.setEnabled(false);
          btn_skill3.setEnabled(false);
          btn_skill4.setEnabled(false);
    }
    
    
    void EnableMana(int v){//ligar, desligar e atualizar mana//
            Sl_Mana.setMaximum(manaAtual);
            Sl_Mana.setValue(0);
            btn_skill1.setEnabled(false);
        if(v == 1){
            Sl_Mana.setEnabled(true);
            btn_skill1.setEnabled(true);
        }else{
            Sl_Mana.setEnabled(false);
        }
    }
    
    void EnableAcao (int v){ //ligar ação//
         if(v == 1){
            btn_Go.setEnabled(true);
            tf_acao.setEnabled(true);
        }else{
            btn_Go.setEnabled(false);
            tf_acao.setEnabled(false);
        }
    }
    
    void conferirV() { //conferir vida atual do jogador//
        lbl_vida.setText(String.valueOf(vidaJ));
        if(vidaJ <= 0){
            
            EnableAcao(0);
            EnableMana(0);
            DisebleSkill();
            EnableDado(0);
        
            
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Você está morto, mas não desanime, pode tentar de novo!");
            
            Menu i = new Menu();
            i.setVisible(true);
            dispose();
        }
    }
    
    
    //-------------------- TROLL DA MONTANHA ------------------//
    
    void Historia (int v){
        int dadoM;
        switch (v) {
            case 1 -> {
                ta_Campo.setText("""
                                    Andando pela floresta, voc\u00ea encontra um monstruoso ser de aproximadamente 2 metros de altura, em seu corpo todo existe uma pelagem
                                    de cor avermelhada, seus olhos brilham em cor de fogo, e com um rugido olhando para voce ele mostra suas grandes e fortes garras
                                 
                                    --- VOCE SE DEPARA COM UM TROLL DA MONTANHA, O QUE VOCE FAZ? ---""");
                ta_acao.setText("""
                                    FUGIR - tentar correr mais rapido que o troll
                                    evitando o combate!
                                    LUTAR - lutar com seus poderes magicos!""");
                EnableAcao(1);
                h = ta_Campo.getText();
                ContH = ContH + 1;
            }
            case 2 -> {
                if(null == acao){
                    ta_Campo.setText(h + "\n '" + acao + "' não é uma ação valida, favor consulte o quadro de ação e tente novamete!\n\n"
                            + "--- O QUE VOCÊ FAZ? ---");
                    EnableAcao(1);
                    h = ta_Campo.getText();
                    ContH = 2;
                }else switch (acao) {
                case "FUGIR", "fugir" -> {
                    ta_Campo.setText(h + "\n\n FUGIR \n\n --- favor Role um dado! ---");
                    EnableAcao(0);
                    EnableDado(1);
                    h = ta_Campo.getText();
                    ContH = ContH + 1;
                    }
                case "LUTAR", "lutar" -> {
                    ta_Campo.setText(h + "\n\n LUTAR \n\n ---MODO DE COMBATE---\n\n"
                            + "O troll te olha com seus olhos vermelhos e da um grito estridente \n\nSelecione o ataque que quer utilizar!");
                    EnableAcao(0);
                    EnableMana(1);
                    h = ta_Campo.getText();
                    }
                default -> {
                    ta_Campo.setText(h + "\n '" + acao + "' não é uma ação valida, favor consulte o quadro de ação e tente novamete!\n\n"
                            + "--- O QUE VOCÊ FAZ? ---");
                    EnableAcao(1);
                    h = ta_Campo.getText();
                    ContH = 2;
                }
            }
            }
            case 3 -> {
                dadoM = Rolard20();
                ta_Campo.setText(h + "\nO TROLL TENTA TE PERSEGUIR! \n\nDADO DO TROLL: " + dadoM);
                h = ta_Campo.getText();
                if(dadoM < dado){
                    EnableAcao(0);
                    EnableMana(0);
                    DisebleSkill();
                    EnableDado(0);
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "Voce Foge como um covarde enquanto o troll ri!");
                    
                    Menu i = new Menu();
                    i.setVisible(true);
                    dispose();
                    
                }else{
                    dadoM = Rolard4();
                    vidaJ = vidaJ - dadoM;
                    
                    ta_Campo.setText(h + "\nPorém o troll é rapido de mais e acerta com as garras em suas costas\n "
                            + "Voce toma: " + dadoM + " de dano!");
                    h = ta_Campo.getText();
                    conferirV();
                    
                    ta_Campo.setText(h + "\n--- O que você faz? ---");
                    EnableAcao(1);
                    h = ta_Campo.getText();
                    ContH = 2;
                    
                }
            }
            case 4 -> {
                ta_Campo.setText(h + "\nO troll vendo seu movimento como um raio consegue se esquivar!\n");
                h = ta_Campo.getText();
                ContH = 5;
                Historia(ContH);
            }
            case 5 -> {
                ta_Campo.setText(h + "\n ---VEZ DO TROLL---\n\nO troll tenta te atacar com um forte ataque com suas garras!"
                        + "\n\nFavor role um dado para tentar esquivar");
                ContH = 6;
                EnableDado(1);
                h = ta_Campo.getText();
            }
            case 6 -> {
                dadoM = Rolard20();
                if(dado > dadoM ){
                    ta_Campo.setText(h + "\n\nDado do troll:" + dadoM + "\n\nUsando seus poderes magicos, em sua visao voce consegue prever "
                            + "o ataque do troll e esquivar com maestria!");
                    h = ta_Campo.getText();
                }else{
                    dadoM = Rolard6();
                    vidaJ = vidaJ - dadoM;
                    
                    ta_Campo.setText(h + "\n\nDado do troll:" + dadoM + "\n\nO Troll consegue disparar um rapido ataque com suas grandes garras te atingindo"
                            + "\nVoce toma: " + dadoM + " de dano!");
                    h = ta_Campo.getText();
                    conferirV();
                }   ContH = 7;
                Historia(ContH);
            }
            case 7 -> {
                manaAtual = manaAtual + 1;
                ta_Campo.setText(h + "\n\n ---SUA VEZ SELECIONE O SEU ATAQUE---\nVocê recuperou 1 de mana!");
                EnableMana(1);
                h = ta_Campo.getText();
            }
            default -> {
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "Erro inesperado ao carregar historia, favor reinicie o jogo");
                Menu i = new Menu();
                i.setVisible(true);
                dispose();
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_acao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_acao = new javax.swing.JTextArea();
        Sl_Mana = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_Campo = new javax.swing.JTextArea();
        btn_skill1 = new javax.swing.JButton();
        btn_skill4 = new javax.swing.JButton();
        btn_skill2 = new javax.swing.JButton();
        btn_skill3 = new javax.swing.JButton();
        btn_Go = new javax.swing.JButton();
        lbl_Vdado = new javax.swing.JLabel();
        lbl_vida = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_mana = new javax.swing.JLabel();
        btn_Roll = new javax.swing.JButton();
        lbl_Ataque = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MAGOS A ACAO");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Vida:");

        jLabel2.setText("AÇÃO");

        tf_acao.setToolTipText("");

        ta_acao.setEditable(false);
        ta_acao.setColumns(20);
        ta_acao.setRows(5);
        jScrollPane1.setViewportView(ta_acao);

        Sl_Mana.setMaximum(10);
        Sl_Mana.setMinorTickSpacing(1);
        Sl_Mana.setOrientation(javax.swing.JSlider.VERTICAL);
        Sl_Mana.setPaintLabels(true);
        Sl_Mana.setPaintTicks(true);
        Sl_Mana.setValue(0);
        Sl_Mana.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Sl_ManaStateChanged(evt);
            }
        });

        jLabel1.setText("AÇÕES");

        ta_Campo.setEditable(false);
        ta_Campo.setColumns(20);
        ta_Campo.setRows(5);
        jScrollPane2.setViewportView(ta_Campo);

        btn_skill1.setText("Punho de Fogo");
        btn_skill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_skill1ActionPerformed(evt);
            }
        });

        btn_skill4.setText("Buraco Negro");
        btn_skill4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_skill4ActionPerformed(evt);
            }
        });

        btn_skill2.setText("Cura Mistica");
        btn_skill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_skill2ActionPerformed(evt);
            }
        });

        btn_skill3.setText("Lança de Gelo");
        btn_skill3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_skill3ActionPerformed(evt);
            }
        });

        btn_Go.setText("GO");
        btn_Go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GoActionPerformed(evt);
            }
        });

        lbl_Vdado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_Vdado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Vdado.setText("DADO");

        lbl_vida.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_vida.setText("20");

        jLabel8.setText("Mana:");

        lbl_mana.setText("10");

        btn_Roll.setText("Roll");
        btn_Roll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RollActionPerformed(evt);
            }
        });

        lbl_Ataque.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_Ataque.setText("ATAQUES");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_acao, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Go, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(lbl_Ataque))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_vida))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btn_skill1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_skill3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(32, 32, 32)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_skill2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_skill4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addComponent(Sl_Mana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(btn_Roll, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lbl_Vdado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_mana, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(168, 168, 168))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(lbl_Ataque)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_skill2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_skill1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_skill3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_skill4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tf_acao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Go)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(lbl_vida))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(lbl_mana)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sl_Mana, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_Vdado)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Roll)
                                .addGap(27, 27, 27)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RollActionPerformed
            dado = Rolard20();
            lbl_Vdado.setText(String.valueOf(dado));
            
            EnableDado(0);
            Historia(ContH);
    }//GEN-LAST:event_btn_RollActionPerformed

    private void btn_skill4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_skill4ActionPerformed
        skill = 4;
        Ataque(skill);
    }//GEN-LAST:event_btn_skill4ActionPerformed

    private void Sl_ManaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Sl_ManaStateChanged
        manaJog = Sl_Mana.getValue();
        lbl_mana.setText(String.valueOf(manaJog));
        EnableSkill(manaJog);
    }//GEN-LAST:event_Sl_ManaStateChanged

    private void btn_skill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_skill1ActionPerformed
        skill = 1;
        Ataque(skill);
    }//GEN-LAST:event_btn_skill1ActionPerformed

    private void btn_skill2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_skill2ActionPerformed
       skill = 2;
       Ataque(skill);
    }//GEN-LAST:event_btn_skill2ActionPerformed

    private void btn_skill3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_skill3ActionPerformed
        skill = 3;
        Ataque(skill);             
    }//GEN-LAST:event_btn_skill3ActionPerformed

    private void btn_GoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GoActionPerformed
        JFrame jFrame = new JFrame();
        
          if (tf_acao.getText()== null || tf_acao.getText().trim().equals("")){  
            JOptionPane.showMessageDialog(jFrame, "A ação não pode ser em branco!");
        }else{
            acao = tf_acao.getText();
            Historia(ContH);
            tf_acao.setText("");
         }
    }//GEN-LAST:event_btn_GoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceJogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider Sl_Mana;
    private javax.swing.JButton btn_Go;
    private javax.swing.JButton btn_Roll;
    private javax.swing.JButton btn_skill1;
    private javax.swing.JButton btn_skill2;
    private javax.swing.JButton btn_skill3;
    private javax.swing.JButton btn_skill4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Ataque;
    private javax.swing.JLabel lbl_Vdado;
    private javax.swing.JLabel lbl_mana;
    private javax.swing.JLabel lbl_vida;
    private javax.swing.JTextArea ta_Campo;
    private javax.swing.JTextArea ta_acao;
    private javax.swing.JTextField tf_acao;
    // End of variables declaration//GEN-END:variables
}
