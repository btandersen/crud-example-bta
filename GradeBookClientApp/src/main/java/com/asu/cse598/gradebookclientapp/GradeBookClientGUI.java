/*
 * Brandon Andersen
 * 1000878186
 * CSE 598
 * Spring 2013
 * Professor Calliss
 * 
 * Grade Book Client GUI
 */
package com.asu.cse598.gradebookclientapp;

import com.asu.cse598.gradebooklibrary.GradeBookJsonMapper;
import com.asu.cse598.gradebooklibrary.GradedWorkItem;
import com.asu.cse598.gradebooklibrary.Student;
import com.asu.cse598.gradebooklibrary.WorkItem;
import com.asu.cse598.gradebooklibrary.WorkItemType;
import com.sun.jersey.api.client.ClientResponse;
import java.util.ArrayList;

/**
 *
 * @author Brandon
 */
public class GradeBookClientGUI extends javax.swing.JFrame {

    private GradeBookProxy gradeBookProxy; // proxy to resources
    private GradeBookJsonMapper mapper; // json to obj mapper

    /**
     * Creates new form GradeBookClientGUI
     */
    public GradeBookClientGUI() {
        initComponents();
        this.gradeBookProxy = new GradeBookProxy();
        this.mapper = new GradeBookJsonMapper();
        this.updateAll();
    }

    // update all the drop down lists
    private void updateAll() {
        this.updateStudentList();
        this.updateWorkItemTypeList();
        this.updateWorkItemList();
        this.updateGradedWorkItemList();
        this.repaint();
    }

    // update the student list
    private void updateStudentList() {
        ClientResponse response = this.gradeBookProxy.getStudentList(ClientResponse.class);
        if (response.getStatus() == 200) {
            try {
                ArrayList<Student> studentList = this.mapper.studentListJsonToList(response.getEntity(String.class));
                this.jComboBoxStudents.removeAllItems();
                for (int i = 0; i < studentList.size(); ++i) {
                    this.jComboBoxStudents.addItem(studentList.get(i)._id);
                }
                this.jLabelStudentListStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            } catch (Exception e) {
                this.jLabelStudentListStatus.setText(e.getMessage());
            }
        } else {
            this.jLabelStudentListStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
        }
        this.repaint();
    }

    // update the work item type list
    private void updateWorkItemTypeList() {
        ClientResponse response = this.gradeBookProxy.getWorkItemTypeList(ClientResponse.class);
        if (response.getStatus() == 200) {
            try {
                ArrayList<WorkItemType> workItemTypeList = this.mapper.workItemTypeListJsonToList(response.getEntity(String.class));
                this.jComboBoxWorkItemTypeList.removeAllItems();
                for (int i = 0; i < workItemTypeList.size(); ++i) {
                    this.jComboBoxWorkItemTypeList.addItem(workItemTypeList.get(i)._id);
                }
                this.jLabelGetWorkItemTypeStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            } catch (Exception e) {
                this.jLabelGetWorkItemTypeStatus.setText(e.getMessage());
            }
        } else {
            this.jLabelGetWorkItemTypeStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
        }
        this.repaint();
    }

    // update the work item list
    private void updateWorkItemList() {
        ClientResponse response = this.gradeBookProxy.getWorkItemList(ClientResponse.class);
        if (response.getStatus() == 200) {
            try {
                ArrayList<WorkItem> workItemList = this.mapper.workItemListJsonToList(response.getEntity(String.class));
                this.workItemListComboBox.removeAllItems();
                for (int i = 0; i < workItemList.size(); ++i) {
                    this.workItemListComboBox.addItem(workItemList.get(i)._id);
                }
            } catch (Exception e) {
            }
        }
        this.repaint();
    }

    // update the graded work item list
    private void updateGradedWorkItemList() {
        ClientResponse response = this.gradeBookProxy.getGradedWorkItemList(ClientResponse.class);
        if (response.getStatus() == 200) {
            try {
                ArrayList<GradedWorkItem> gradedWorkItemList = this.mapper.gradedWorkItemListJsonToList(response.getEntity(String.class));
                this.gradedWorkItemListComboBox.removeAllItems();
                for (int i = 0; i < gradedWorkItemList.size(); ++i) {
                    this.gradedWorkItemListComboBox.addItem(gradedWorkItemList.get(i)._id);
                }
            } catch (Exception e) {
            }
        }
        this.repaint();
    }

    // update the graded work item info based on passed graded work item
    private void updateGradedWorkItemLabels(GradedWorkItem gradedWorkItem) {
        if (null != gradedWorkItem) {
            this.gradedWorkItemIdLabel.setText(gradedWorkItem._id);
            this.gradedWorkItemTypeLabel.setText(gradedWorkItem.workItemType_id);
            this.gradedWorkItemStudentIdLabel.setText(gradedWorkItem.student_id);
            this.gradedWorkItemTotalPointsLabel.setText(Integer.toString(gradedWorkItem.totalPoints));
            this.gradedWorkItemPointsLabel.setText(Integer.toString(gradedWorkItem.points));
            this.gradedWorkItemGradePctLabel.setText(Double.toString(100 * (double) gradedWorkItem.points / gradedWorkItem.totalPoints) + "%");
            this.gradedWorkItemCommentsLabel.setText(gradedWorkItem.comments);
            this.gradedWorkItemAppealLabel.setText(gradedWorkItem.appeal);
            this.gradedWorkItemListComboBox.setSelectedItem(gradedWorkItem._id);
        } else {
            this.gradedWorkItemIdLabel.setText("");
            this.gradedWorkItemTypeLabel.setText("");
            this.gradedWorkItemStudentIdLabel.setText("");
            this.gradedWorkItemTotalPointsLabel.setText("");
            this.gradedWorkItemPointsLabel.setText("");
            this.gradedWorkItemCommentsLabel.setText("");
            this.gradedWorkItemAppealLabel.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonCreateStudent = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCreateStudentResult = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelStudentId = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxStudents = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabelStudentListStatus = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        studentDeleteButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldWorkItem_Id = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldWorkItemType_GradWgt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldWorkItemType_UnderGradWgt = new javax.swing.JTextField();
        jButtonCreateWorkItemType = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabelWorkItemType_Id = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabelCreateWorkItemTypeStatus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxWorkItemTypeList = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabelGradWgt = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabelUnderGradWgt = new javax.swing.JLabel();
        jButtonGetWorkItemType = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabelGetWorkItemTypeStatus = new javax.swing.JLabel();
        workItemTypeUpdateButton = new javax.swing.JButton();
        workItemTypeDeleteButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        workItemIdInput = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        workItemTotalPointsInput = new javax.swing.JTextField();
        workItemCreateButton = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        workItemListComboBox = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        selWorkItemTypeLabel = new javax.swing.JLabel();
        selWorkItemPointsLabel = new javax.swing.JLabel();
        workItemGetSelectedButton = new javax.swing.JButton();
        workItemUpdateSelectedButton = new javax.swing.JButton();
        workItemDeleteSelectedButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        workItemStatus = new javax.swing.JLabel();
        workItemType_IdLabel = new javax.swing.JLabel();
        workItemPointsLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        gradedWorkItemListComboBox = new javax.swing.JComboBox();
        gradedWorkItemGetButton = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        gradedWorkItemStatus = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        gradedWorkItemIdLabel = new javax.swing.JLabel();
        gradedWorkItemStudentIdLabel = new javax.swing.JLabel();
        gradedWorkItemTypeLabel = new javax.swing.JLabel();
        gradedWorkItemTotalPointsLabel = new javax.swing.JLabel();
        gradedWorkItemPointsLabel = new javax.swing.JLabel();
        gradedWorkItemCommentsLabel = new javax.swing.JLabel();
        gradedWorkItemAppealLabel = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        gradedWorkItemGradePctLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        gradedWorkItemPointsInput = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        gradedWorkItemCommentsInput = new javax.swing.JTextField();
        gradedWorkItemCreateButton = new javax.swing.JButton();
        gradedWorkItemUpdateButton = new javax.swing.JButton();
        gradedWorkItemDeleteButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        gradedWorkItemAppealInput = new javax.swing.JTextField();
        gradedWorkItemSubmitAppealButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("First Name");

        jTextFieldFirstName.setText("firstname");

        jLabel2.setText("Last Name");

        jTextFieldLastName.setText("lastname");

        jLabel3.setText("Status");

        jButtonCreateStudent.setText("Create Student");
        jButtonCreateStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCreateStudentMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Add Students");

        jTextFieldCreateStudentResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("New Student ID");

        jLabelStudentId.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCreateStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFirstName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLastName))
                    .addComponent(jTextFieldCreateStudentResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabelStudentId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCreateStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCreateStudentResult, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelStudentId, jTextFieldCreateStudentResult});

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Available Students (by ID)");

        jLabelStudentListStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Status");

        studentDeleteButton.setText("Delete Selected Student");
        studentDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxStudents, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelStudentListStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(studentDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStudentListStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentDeleteButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Add Work Item Types");

        jLabel9.setText("Type (e.g. exam)");

        jTextFieldWorkItem_Id.setText("type_name_here");

        jLabel10.setText("Grad Wgt (e.g 0.25)");

        jTextFieldWorkItemType_GradWgt.setText("0.25");

        jLabel11.setText("Undergrad Wgt (e.g 0.35)");

        jTextFieldWorkItemType_UnderGradWgt.setText("0.35");

        jButtonCreateWorkItemType.setText("Create Work Item Type");
        jButtonCreateWorkItemType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateWorkItemTypeActionPerformed(evt);
            }
        });

        jLabel12.setText("Work Item Type ID");

        jLabelWorkItemType_Id.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Status");

        jLabelCreateWorkItemTypeStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCreateWorkItemType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(15, 15, 15)
                        .addComponent(jTextFieldWorkItemType_UnderGradWgt))
                    .addComponent(jLabelWorkItemType_Id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCreateWorkItemTypeStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldWorkItem_Id, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jTextFieldWorkItemType_GradWgt)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldWorkItem_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldWorkItemType_GradWgt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldWorkItemType_UnderGradWgt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCreateWorkItemType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelWorkItemType_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCreateWorkItemTypeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Available Work Item Types");

        jLabel15.setText("Grad:");

        jLabel17.setText("Undergrad:");

        jButtonGetWorkItemType.setText("Get Selected Details");
        jButtonGetWorkItemType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetWorkItemTypeActionPerformed(evt);
            }
        });

        jLabel16.setText("Status");

        jLabelGetWorkItemTypeStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        workItemTypeUpdateButton.setText("Update Selected");
        workItemTypeUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workItemTypeUpdateButtonActionPerformed(evt);
            }
        });

        workItemTypeDeleteButton.setText("Delete Selected");
        workItemTypeDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workItemTypeDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGetWorkItemType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelGradWgt))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelUnderGradWgt))
                    .addComponent(jLabelGetWorkItemTypeStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxWorkItemTypeList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(workItemTypeUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workItemTypeDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxWorkItemTypeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabelGradWgt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabelUnderGradWgt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGetWorkItemType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemTypeUpdateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemTypeDeleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelGetWorkItemTypeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Work Items");

        jLabel19.setText("Work Item ID (e.g. Assignment1)");

        workItemIdInput.setText("name");

        jLabel20.setText("Work Item Total Points (e.g. 100)");

        workItemTotalPointsInput.setText("100");

        workItemCreateButton.setText("Create");
        workItemCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workItemCreateButtonActionPerformed(evt);
            }
        });

        jLabel21.setText("Click <Create> to create with selected Work Item Type");

        jLabel22.setText("Available Work Items");

        jLabel23.setText("Type:");

        jLabel24.setText("Points:");

        workItemGetSelectedButton.setText("Get Selected Details");
        workItemGetSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workItemGetSelectedButtonActionPerformed(evt);
            }
        });

        workItemUpdateSelectedButton.setText("Update Selected");
        workItemUpdateSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workItemUpdateSelectedButtonActionPerformed(evt);
            }
        });

        workItemDeleteSelectedButton.setText("Delete Selected");
        workItemDeleteSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workItemDeleteSelectedButtonActionPerformed(evt);
            }
        });

        jLabel25.setText("Status");

        workItemStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workItemTotalPointsInput))
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workItemIdInput))
                    .addComponent(workItemCreateButton)
                    .addComponent(jLabel25)
                    .addComponent(workItemStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(workItemPointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(workItemType_IdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selWorkItemPointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selWorkItemTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(workItemListComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workItemDeleteSelectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workItemUpdateSelectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(workItemGetSelectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(workItemIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(workItemTotalPointsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemCreateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(selWorkItemTypeLabel)
                    .addComponent(workItemType_IdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(selWorkItemPointsLabel)
                    .addComponent(workItemPointsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemGetSelectedButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemUpdateSelectedButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemDeleteSelectedButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workItemStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Graded Work Items | Create a graded work item for the selected student and work item");

        jLabel31.setText("Available Graded Work Items");

        gradedWorkItemGetButton.setText("Get Selected Details");
        gradedWorkItemGetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradedWorkItemGetButtonActionPerformed(evt);
            }
        });

        jLabel32.setText("Status");

        gradedWorkItemStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel34.setText("ID:");

        jLabel35.setText("Student ID:");

        jLabel36.setText("Total Points:");

        jLabel37.setText("Points Received:");

        jLabel38.setText("Instructor Comments:");

        jLabel39.setText("Student Appeal:");

        jLabel40.setText("Type:");

        jLabel33.setText("Grade %:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gradedWorkItemAppealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradedWorkItemCommentsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel40)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel33))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gradedWorkItemPointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradedWorkItemTotalPointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradedWorkItemTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradedWorkItemIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradedWorkItemStudentIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gradedWorkItemGradePctLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(gradedWorkItemIdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(gradedWorkItemStudentIdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(gradedWorkItemTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(gradedWorkItemTotalPointsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(gradedWorkItemPointsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(gradedWorkItemGradePctLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(gradedWorkItemCommentsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(gradedWorkItemAppealLabel))
                .addContainerGap())
        );

        jLabel28.setText("Points Received:");

        gradedWorkItemPointsInput.setText("0");

        jLabel29.setText("Instructor Comments:");

        gradedWorkItemCommentsInput.setText("comment");

        gradedWorkItemCreateButton.setText("Create");
        gradedWorkItemCreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradedWorkItemCreateButtonActionPerformed(evt);
            }
        });

        gradedWorkItemUpdateButton.setText("Update Selected");
        gradedWorkItemUpdateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gradedWorkItemUpdateButtonMouseClicked(evt);
            }
        });

        gradedWorkItemDeleteButton.setText("Delete Selected");
        gradedWorkItemDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradedWorkItemDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28)
                            .addComponent(gradedWorkItemPointsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gradedWorkItemCommentsInput)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gradedWorkItemUpdateButton)
                                    .addComponent(gradedWorkItemDeleteButton)
                                    .addComponent(gradedWorkItemCreateButton))
                                .addGap(0, 362, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gradedWorkItemPointsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradedWorkItemCommentsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradedWorkItemCreateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradedWorkItemUpdateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradedWorkItemDeleteButton)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Instructor", jPanel8);

        jLabel30.setText("Student Appeal:");

        gradedWorkItemAppealInput.setText("appeal");

        gradedWorkItemSubmitAppealButton.setText("Submit Appeal");
        gradedWorkItemSubmitAppealButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradedWorkItemSubmitAppealButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gradedWorkItemAppealInput)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(gradedWorkItemSubmitAppealButton))
                        .addGap(0, 372, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradedWorkItemAppealInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradedWorkItemSubmitAppealButton)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Student", jPanel9);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(gradedWorkItemListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gradedWorkItemStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(gradedWorkItemGetButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gradedWorkItemListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gradedWorkItemGetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gradedWorkItemStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // create a new student event
    private void jButtonCreateStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCreateStudentMouseClicked
        ClientResponse response;
        String fName = this.jTextFieldFirstName.getText();
        String lName = this.jTextFieldLastName.getText();
        Student student = new Student();
        student.firstName = fName;
        student.lastName = lName;
        try {
            response = this.gradeBookProxy.addStudent(this.mapper.studentObjToJson(student));
            this.jTextFieldCreateStudentResult.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                student = this.mapper.studentJsonToObj(response.getEntity(String.class));
                this.jLabelStudentId.setText(student._id);
            }
            this.updateStudentList();
        } catch (Exception e) {
            this.jTextFieldCreateStudentResult.setText(e.getMessage());
        }
    }//GEN-LAST:event_jButtonCreateStudentMouseClicked

    // create a new work item type event
    private void jButtonCreateWorkItemTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateWorkItemTypeActionPerformed
        ClientResponse response;
        try {
            String workItemTypename = this.jTextFieldWorkItem_Id.getText();
            double gradWgt = Double.parseDouble(this.jTextFieldWorkItemType_GradWgt.getText());
            double underGradWgt = Double.parseDouble(this.jTextFieldWorkItemType_UnderGradWgt.getText());
            WorkItemType workItemType = new WorkItemType();
            workItemType._id = workItemTypename;
            workItemType.graduateWeight = gradWgt;
            workItemType.underGraduateWeight = underGradWgt;
            response = this.gradeBookProxy.addWorkItemType(this.mapper.workItemTypeObjToJson(workItemType));
            this.jLabelCreateWorkItemTypeStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                workItemType = this.mapper.workItemTypeJsonToObj(response.getEntity(String.class));
                this.jLabelWorkItemType_Id.setText(workItemType._id);
            }
            this.updateWorkItemTypeList();
        } catch (Exception e) {
            this.jLabelCreateWorkItemTypeStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_jButtonCreateWorkItemTypeActionPerformed

    // get work item type action
    private void jButtonGetWorkItemTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetWorkItemTypeActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.getWorkItemType(ClientResponse.class, this.jComboBoxWorkItemTypeList.getSelectedItem().toString());
            this.jLabelGetWorkItemTypeStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                WorkItemType workItemType = this.mapper.workItemTypeJsonToObj(response.getEntity(String.class));
                this.jLabelGradWgt.setText(Double.toString(workItemType.graduateWeight));
                this.jLabelUnderGradWgt.setText(Double.toString(workItemType.underGraduateWeight));
            }
        } catch (Exception e) {
            this.jLabelGetWorkItemTypeStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_jButtonGetWorkItemTypeActionPerformed

    // create a new work item
    private void workItemCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workItemCreateButtonActionPerformed
        ClientResponse response;
        try {
            WorkItem workItem = new WorkItem();
            workItem._id = this.workItemIdInput.getText();
            workItem.workItemType_id = this.jComboBoxWorkItemTypeList.getSelectedItem().toString();
            workItem.totalPoints = Integer.parseInt(this.workItemTotalPointsInput.getText());
            response = this.gradeBookProxy.addWorkItem(this.mapper.workItemObjToJson(workItem));
            this.workItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                workItem = this.mapper.workItemJsonToObj(response.getEntity(String.class));
                this.workItemType_IdLabel.setText(workItem.workItemType_id);
                this.workItemPointsLabel.setText(Integer.toString(workItem.totalPoints));
            }
            this.updateWorkItemList();
        } catch (Exception e) {
            this.workItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_workItemCreateButtonActionPerformed

    // get selected work item event
    private void workItemGetSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workItemGetSelectedButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.getWorkItem(ClientResponse.class, this.workItemListComboBox.getSelectedItem().toString());
            this.workItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                WorkItem workItem = this.mapper.workItemJsonToObj(response.getEntity(String.class));
                this.workItemType_IdLabel.setText(workItem.workItemType_id);
                this.workItemPointsLabel.setText(Integer.toString(workItem.totalPoints));
            }
        } catch (Exception e) {
            this.workItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_workItemGetSelectedButtonActionPerformed

    // update selected work item event
    private void workItemUpdateSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workItemUpdateSelectedButtonActionPerformed
        ClientResponse response;
        try {
            WorkItem workItem = new WorkItem();
            workItem._id = this.workItemListComboBox.getSelectedItem().toString();
            workItem.workItemType_id = this.jComboBoxWorkItemTypeList.getSelectedItem().toString();
            workItem.totalPoints = Integer.parseInt(this.workItemTotalPointsInput.getText());
            response = this.gradeBookProxy.updateWorkItem(this.mapper.workItemObjToJson(workItem), workItem._id);
            this.workItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                workItem = this.mapper.workItemJsonToObj(response.getEntity(String.class));
                this.workItemType_IdLabel.setText(workItem.workItemType_id);
                this.workItemPointsLabel.setText(Integer.toString(workItem.totalPoints));
                this.updateWorkItemList();
                this.workItemListComboBox.setSelectedItem(workItem._id);
            }
        } catch (Exception e) {
            this.workItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_workItemUpdateSelectedButtonActionPerformed

    // delete selected work item
    private void workItemDeleteSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workItemDeleteSelectedButtonActionPerformed
        ClientResponse response;
        try {
            WorkItem workItem = new WorkItem();
            workItem._id = this.workItemListComboBox.getSelectedItem().toString();
            response = this.gradeBookProxy.deleteWorkItem(workItem._id);
            this.workItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                this.workItemType_IdLabel.setText("");
                this.workItemPointsLabel.setText("");
                this.updateWorkItemList();
            }
        } catch (Exception e) {
            this.workItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_workItemDeleteSelectedButtonActionPerformed

    // create a new graded work item
    private void gradedWorkItemCreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradedWorkItemCreateButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.getWorkItem(ClientResponse.class, this.workItemListComboBox.getSelectedItem().toString());
            if (response.getStatus() == 200) {
                WorkItem workItem = this.mapper.workItemJsonToObj(response.getEntity(String.class));
                GradedWorkItem gradedWorkItem = new GradedWorkItem();
                gradedWorkItem._id = workItem._id;
                gradedWorkItem.workItemType_id = workItem.workItemType_id;
                gradedWorkItem.totalPoints = workItem.totalPoints;
                gradedWorkItem.student_id = this.jComboBoxStudents.getSelectedItem().toString();
                gradedWorkItem.points = Integer.parseInt(this.gradedWorkItemPointsInput.getText());
                gradedWorkItem.comments = this.gradedWorkItemCommentsInput.getText();
                response = this.gradeBookProxy.addGradedWorkItem(this.mapper.workItemObjToJson(gradedWorkItem));
                this.gradedWorkItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
                if (response.getStatus() == 200) {
                    gradedWorkItem = this.mapper.gradedWorkItemJsonToObj(response.getEntity(String.class));
                    this.updateGradedWorkItemLabels(gradedWorkItem);
                }
            }
            this.updateGradedWorkItemList();
        } catch (Exception e) {
            this.gradedWorkItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_gradedWorkItemCreateButtonActionPerformed

    // get the selected work item event
    private void gradedWorkItemGetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradedWorkItemGetButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.getGradedWorkItem(ClientResponse.class, this.gradedWorkItemListComboBox.getSelectedItem().toString());
            this.gradedWorkItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                GradedWorkItem gradedWorkItem = this.mapper.gradedWorkItemJsonToObj(response.getEntity(String.class));
                this.updateGradedWorkItemLabels(gradedWorkItem);
            }
        } catch (Exception e) {
            this.workItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_gradedWorkItemGetButtonActionPerformed

    // update the selected graded work item event
    private void gradedWorkItemUpdateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gradedWorkItemUpdateButtonMouseClicked
        ClientResponse response;
        try {
            response = this.gradeBookProxy.getGradedWorkItem(ClientResponse.class, this.gradedWorkItemListComboBox.getSelectedItem().toString());
            this.gradedWorkItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                GradedWorkItem gradedWorkItem = this.mapper.gradedWorkItemJsonToObj(response.getEntity(String.class));
                gradedWorkItem.points = Integer.parseInt(this.gradedWorkItemPointsInput.getText());
                gradedWorkItem.comments = this.gradedWorkItemCommentsInput.getText();
                response = this.gradeBookProxy.updateGradedWorkItem(this.mapper.gradedWorkItemObjToJson(gradedWorkItem), gradedWorkItem._id);
                this.gradedWorkItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
                if (response.getStatus() == 200) {
                    this.updateGradedWorkItemLabels(gradedWorkItem);
                    this.gradedWorkItemListComboBox.setSelectedItem(gradedWorkItem._id);
                }
            }
        } catch (Exception e) {
            this.gradedWorkItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_gradedWorkItemUpdateButtonMouseClicked

    // delete the selected graded work item event
    private void gradedWorkItemDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradedWorkItemDeleteButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.deleteGradedWorkItem(this.gradedWorkItemListComboBox.getSelectedItem().toString());
            this.gradedWorkItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                this.updateGradedWorkItemLabels(null);
                this.updateGradedWorkItemList();
            }
        } catch (Exception e) {
            this.gradedWorkItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_gradedWorkItemDeleteButtonActionPerformed

    // delete the selected student event
    private void studentDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentDeleteButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.deleteStudent(this.jComboBoxStudents.getSelectedItem().toString());
            if (response.getStatus() == 200) {
                this.updateStudentList();
            }
        } catch (Exception e) {
            this.jLabelStudentListStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_studentDeleteButtonActionPerformed

    // update the selected work item type event
    private void workItemTypeUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workItemTypeUpdateButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.getWorkItemType(ClientResponse.class, this.jComboBoxWorkItemTypeList.getSelectedItem().toString());
            this.jLabelGetWorkItemTypeStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                WorkItemType workItemType = this.mapper.workItemTypeJsonToObj(response.getEntity(String.class));
                workItemType.graduateWeight = Double.parseDouble(this.jTextFieldWorkItemType_GradWgt.getText());
                workItemType.underGraduateWeight = Double.parseDouble(this.jTextFieldWorkItemType_UnderGradWgt.getText());
                response = this.gradeBookProxy.updateWorkItemType(this.mapper.workItemTypeObjToJson(workItemType), workItemType._id);
                this.jLabelGetWorkItemTypeStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
                if (response.getStatus() == 200) {
                    workItemType = this.mapper.workItemTypeJsonToObj(response.getEntity(String.class));
                    this.jLabelGradWgt.setText(Double.toString(workItemType.graduateWeight));
                    this.jLabelUnderGradWgt.setText(Double.toString(workItemType.underGraduateWeight));
                    this.jComboBoxWorkItemTypeList.setSelectedItem(workItemType._id);
                }
            }
        } catch (Exception e) {
            this.jLabelGetWorkItemTypeStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_workItemTypeUpdateButtonActionPerformed

    // delete the selected work item type
    private void workItemTypeDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workItemTypeDeleteButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.deleteWorkItemType(this.jComboBoxWorkItemTypeList.getSelectedItem().toString());
            if (response.getStatus() == 200) {
                this.updateWorkItemTypeList();
            }
        } catch (Exception e) {
            this.jLabelGetWorkItemTypeStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_workItemTypeDeleteButtonActionPerformed

    // submit and appeal for the selected graded work item
    private void gradedWorkItemSubmitAppealButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradedWorkItemSubmitAppealButtonActionPerformed
        ClientResponse response;
        try {
            response = this.gradeBookProxy.getGradedWorkItem(ClientResponse.class, this.gradedWorkItemListComboBox.getSelectedItem().toString());
            this.gradedWorkItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
            if (response.getStatus() == 200) {
                GradedWorkItem gradedWorkItem = this.mapper.gradedWorkItemJsonToObj(response.getEntity(String.class));
                gradedWorkItem.appeal = this.gradedWorkItemAppealInput.getText();
                response = this.gradeBookProxy.updateGradedWorkItem(this.mapper.gradedWorkItemObjToJson(gradedWorkItem), gradedWorkItem._id);
                this.gradedWorkItemStatus.setText(response.getStatus() + "|" + response.getClientResponseStatus());
                if (response.getStatus() == 200) {
                    this.updateGradedWorkItemLabels(gradedWorkItem);
                    this.gradedWorkItemListComboBox.setSelectedItem(gradedWorkItem._id);
                }
            }
        } catch (Exception e) {
            this.gradedWorkItemStatus.setText(e.getMessage());
        }
    }//GEN-LAST:event_gradedWorkItemSubmitAppealButtonActionPerformed

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
            java.util.logging.Logger.getLogger(GradeBookClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradeBookClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradeBookClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradeBookClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GradeBookClientGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField gradedWorkItemAppealInput;
    private javax.swing.JLabel gradedWorkItemAppealLabel;
    private javax.swing.JTextField gradedWorkItemCommentsInput;
    private javax.swing.JLabel gradedWorkItemCommentsLabel;
    private javax.swing.JButton gradedWorkItemCreateButton;
    private javax.swing.JButton gradedWorkItemDeleteButton;
    private javax.swing.JButton gradedWorkItemGetButton;
    private javax.swing.JLabel gradedWorkItemGradePctLabel;
    private javax.swing.JLabel gradedWorkItemIdLabel;
    private javax.swing.JComboBox gradedWorkItemListComboBox;
    private javax.swing.JTextField gradedWorkItemPointsInput;
    private javax.swing.JLabel gradedWorkItemPointsLabel;
    private javax.swing.JLabel gradedWorkItemStatus;
    private javax.swing.JLabel gradedWorkItemStudentIdLabel;
    private javax.swing.JButton gradedWorkItemSubmitAppealButton;
    private javax.swing.JLabel gradedWorkItemTotalPointsLabel;
    private javax.swing.JLabel gradedWorkItemTypeLabel;
    private javax.swing.JButton gradedWorkItemUpdateButton;
    private javax.swing.JButton jButtonCreateStudent;
    private javax.swing.JButton jButtonCreateWorkItemType;
    private javax.swing.JButton jButtonGetWorkItemType;
    private javax.swing.JComboBox jComboBoxStudents;
    private javax.swing.JComboBox jComboBoxWorkItemTypeList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCreateWorkItemTypeStatus;
    private javax.swing.JLabel jLabelGetWorkItemTypeStatus;
    private javax.swing.JLabel jLabelGradWgt;
    private javax.swing.JLabel jLabelStudentId;
    private javax.swing.JLabel jLabelStudentListStatus;
    private javax.swing.JLabel jLabelUnderGradWgt;
    private javax.swing.JLabel jLabelWorkItemType_Id;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jTextFieldCreateStudentResult;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldWorkItemType_GradWgt;
    private javax.swing.JTextField jTextFieldWorkItemType_UnderGradWgt;
    private javax.swing.JTextField jTextFieldWorkItem_Id;
    private javax.swing.JLabel selWorkItemPointsLabel;
    private javax.swing.JLabel selWorkItemTypeLabel;
    private javax.swing.JButton studentDeleteButton;
    private javax.swing.JButton workItemCreateButton;
    private javax.swing.JButton workItemDeleteSelectedButton;
    private javax.swing.JButton workItemGetSelectedButton;
    private javax.swing.JTextField workItemIdInput;
    private javax.swing.JComboBox workItemListComboBox;
    private javax.swing.JLabel workItemPointsLabel;
    private javax.swing.JLabel workItemStatus;
    private javax.swing.JTextField workItemTotalPointsInput;
    private javax.swing.JButton workItemTypeDeleteButton;
    private javax.swing.JButton workItemTypeUpdateButton;
    private javax.swing.JLabel workItemType_IdLabel;
    private javax.swing.JButton workItemUpdateSelectedButton;
    // End of variables declaration//GEN-END:variables
}
